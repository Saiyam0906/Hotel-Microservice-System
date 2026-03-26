package com.example.User.Service;




import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.User.Entity.User;
import com.example.User.Exception.ResourceAlreadyExistsException;
import com.example.User.Exception.ResourseNotFound;
import com.example.User.Interface.S3Interface;
import com.example.User.Interface.UserInterface;
import com.example.User.MapStruct.UserMapper;
import com.example.User.Repository.UserRepository;
import com.example.User.Request.UserRequestDto;
import com.example.User.Response.ProfilePhotoUploadResponse;
import com.example.User.Response.UserResponseDto;
import com.example.User.Update.UserUpdateDto;
import com.example.User.enums.UserStatus;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserInterface{
	
	private final UserRepository repository;
	private final UserMapper mapper;
	private final S3Interface s3Interface;
	
	private static final String PROFILE_PHOTO_PREFIX = "profile-photos/";
	
	private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
		    "image/jpeg",
		    "image/png",
		    "image/jpg"
		);

	@Override
	@Transactional
	public UserResponseDto createUser(UserRequestDto requestDto) {
		
		  log.info("Creating user with email: {}", requestDto.getEmail());
		  
		  String normalizedEmail = requestDto.getEmail().toLowerCase().trim();
		  
		  if (repository.findByEmail(normalizedEmail).isPresent()) {
		        throw new ResourceAlreadyExistsException(
		            "User already exists with email: " + normalizedEmail
		        );
		    }
		 
		 User user=mapper.toEntity(requestDto);
		 user.setEmail(normalizedEmail);
		 user.setStatus(UserStatus.ACTIVE);
		 User saved=repository.save(user);
		
		 log.info("User created successfully with id: {}", saved.getId());
		return mapper.toResponseDto(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponseDto getUserById(Long id) {
		log.info("Fetching user with id: {}", id);

        User user = repository.findById(id)
            .orElseThrow(() -> new ResourseNotFound(
                "User not found with id: " + id
            ));

        return mapper.toResponseDto(user);
	}

	@Override
	@Transactional
	public UserResponseDto updateUser(Long id, UserUpdateDto updateDto) {
		log.info("Updating user with id: {}", id);
		

        User user = repository.findById(id)
            .orElseThrow(() -> new ResourseNotFound(
                "User not found with id: " + id
            ));

        mapper.updateEntityFromDto(updateDto, user); // assumes MapStruct update method
        User saved = repository.save(user);

        log.info("User updated successfully with id: {}", saved.getId());
        return mapper.toResponseDto(saved);
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		  log.info("Deleting user with id: {}", id);
		  
		  User user = repository.findById(id)
		            .orElseThrow(() -> new ResourseNotFound(
		                "User not found with id: " + id
		            ));
		  
		  if(user.getProfilePhotoKey()!=null) {
			  log.info("Deleting profile photo from S3 for user id: {}", id);
	            s3Interface.deleteObject(user.getProfilePhotoKey());
		  }
		  
		  repository.delete(user);
	        log.info("User deleted successfully with id: {}", id);
		
	}

	@Override
    @Transactional
    public UserResponseDto updateProfilePhoto(Long id, String objectKey) {
        log.info("Updating profile photo for user id: {}", id);

        User user = repository.findById(id)
            .orElseThrow(() -> new ResourseNotFound(
                "User not found with id: " + id
            ));

        // Delete old photo from S3 if one already exists
        if (user.getProfilePhotoKey() != null) {
            log.info("Removing old profile photo from S3 for user id: {}", id);
            s3Interface.deleteObject(user.getProfilePhotoKey());
        }

        user.setProfilePhotoKey(objectKey);
        User saved = repository.save(user);

        log.info("Profile photo updated successfully for user id: {}", id);
        return mapper.toResponseDto(saved);
    }
	
	@Override
	@Transactional
    public ProfilePhotoUploadResponse generateProfilePhotoUploadUrl(Long id, String contentType) {
        log.info("Generating presigned upload URL for user id: {}", id);
        
        if (!ALLOWED_CONTENT_TYPES.contains(contentType)) {        // FIX: validate content type
            throw new IllegalArgumentException(
                "Invalid content type: " + contentType +
                ". Allowed types: " + ALLOWED_CONTENT_TYPES
            );
        }
        
        repository.findById(id)                              
        .orElseThrow(() -> new ResourseNotFound(        
            "User not found with id: " + id             
        ));     

        String objectKey = PROFILE_PHOTO_PREFIX + id + "/" + UUID.randomUUID();
        String presignedUrl = s3Interface.generateUploadPresignedUrl(objectKey, contentType);

        log.info("Presigned URL generated for user id: {}", id);
        return new ProfilePhotoUploadResponse(presignedUrl, objectKey);
    }

    
   

}
