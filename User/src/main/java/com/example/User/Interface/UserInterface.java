package com.example.User.Interface;

import com.example.User.Request.UserRequestDto;
import com.example.User.Response.ProfilePhotoUploadResponse;
import com.example.User.Response.UserResponseDto;
import com.example.User.Update.UserUpdateDto;

public interface UserInterface {

	UserResponseDto createUser(UserRequestDto requestDto);
	
	UserResponseDto getUserById(Long id);
	
	UserResponseDto updateUser(Long id, UserUpdateDto updateDto);
	
    void deleteUser(Long id);
    
    UserResponseDto updateProfilePhoto(Long id, String objectKey);

	ProfilePhotoUploadResponse generateProfilePhotoUploadUrl(Long id, String contentType);
    
    
}
