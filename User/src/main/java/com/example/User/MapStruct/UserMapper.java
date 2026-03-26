package com.example.User.MapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.User.Entity.User;
import com.example.User.Request.UserRequestDto;
import com.example.User.Response.UserResponseDto;
import com.example.User.Update.UserUpdateDto;



@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
	
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "profilePhotoKey", ignore = true)
	User toEntity(UserRequestDto dto);
	 
	@Mapping(target = "profileImageUrl", expression = "java(user.getProfilePhotoKey() != null ? \"https://hotel-user-profile-photos.s3.ap-south-1.amazonaws.com/\" + user.getProfilePhotoKey() : null)")
	 UserResponseDto toResponseDto(User user);
	 
	 @Mapping(target = "id", ignore = true)
	 @Mapping(target = "email", ignore = true)
	 @Mapping(target = "status", ignore = true)
	 @Mapping(target = "address", ignore = true)
	 @Mapping(target = "createdAt", ignore = true)
	 @Mapping(target = "updatedAt", ignore = true)
	 @Mapping(target = "profilePhotoKey", ignore = true)
	 void updateEntityFromDto(UserUpdateDto dto, @MappingTarget User user);

}
