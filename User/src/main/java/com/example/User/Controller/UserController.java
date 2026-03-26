package com.example.User.Controller;



import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.User.Interface.UserInterface;
import com.example.User.Request.UserRequestDto;
import com.example.User.Response.ApiResponse;
import com.example.User.Response.ProfilePhotoUploadResponse;
import com.example.User.Response.UserResponseDto;
import com.example.User.Update.UserUpdateDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

	
	private final UserInterface userService;
	
	private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
		    "image/jpeg",
		    "image/png",
		    "image/jpg"
		);
	
	@PostMapping
	public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@Valid @RequestBody UserRequestDto RequestDto){
		
		log.info("POST /api/v1/users - Creating user with email: {}", RequestDto.getEmail());
        UserResponseDto response = userService.createUser(RequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created successfully", response));
		
	}
	
	@GetMapping("/{id}")
	 public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(
	            @PathVariable @Positive(message = "User ID must be a positive number") Long id) {

	        log.info("GET /api/v1/users/{} - Fetching user", id);
	        
	        UserResponseDto response = userService.getUserById(id);
	        return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", response));
	    }
	
	@PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUser(
            @PathVariable @Positive Long id,
            @Valid @RequestBody UserUpdateDto updateDto) {

        log.info("PUT /api/v1/users/{} - Updating user", id);

        UserResponseDto response = userService.updateUser(id, updateDto);

        return ResponseEntity.ok(
                ApiResponse.success("User updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable @Positive Long id) {

        log.info("DELETE /api/v1/users/{} - Deleting user", id);

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/profile-photo")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateProfilePhoto(
            @PathVariable @Positive Long id,
            @RequestParam String objectKey) {

        log.info("POST /api/v1/users/{}/profile-photo - Updating profile photo", id);

        UserResponseDto response = userService.updateProfilePhoto(id, objectKey);

        return ResponseEntity.ok(
                ApiResponse.success("Profile photo updated successfully", response));
    }

    @PostMapping("/{id}/profile-photo/upload-url")
    public ResponseEntity<ApiResponse<ProfilePhotoUploadResponse>> generateProfilePhotoUploadUrl(
            @PathVariable @Positive Long id,
            @RequestParam String contentType) {

        log.info("POST /api/v1/users/{}/profile-photo/upload-url - Generating upload URL", id);
        
        if (!ALLOWED_CONTENT_TYPES.contains(contentType)) {        
            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)     
                    .body(ApiResponse.failure(
                        "Invalid content type: " + contentType +
                        ". Allowed types: image/jpeg, image/png"
                    ));
        }

        ProfilePhotoUploadResponse response =
                userService.generateProfilePhotoUploadUrl(id, contentType);

        return ResponseEntity.ok(
                ApiResponse.success("Upload URL generated successfully", response));
    }
	
	
	
}
