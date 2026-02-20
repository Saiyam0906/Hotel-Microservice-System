package com.example.User.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.User.Entity.UserEntity;
import com.example.User.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
	 UserEntity user1=	userService.saveUser(user);
	 return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUser(userId));
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<UserEntity>> getAllUser(){
		List<UserEntity> allUser=userService.getAlluser();
		return ResponseEntity.ok(allUser);
	}
	
}
