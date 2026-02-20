package com.example.User.Interface;


import java.util.List;


import com.example.User.Entity.UserEntity;

public interface UserInterface {

	UserEntity saveUser(UserEntity user);
	
	List<UserEntity> getAlluser();
	
	UserEntity getUser(Long userId);
}
