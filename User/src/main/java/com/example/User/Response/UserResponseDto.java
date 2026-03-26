package com.example.User.Response;


import java.time.LocalDate;

import java.time.LocalDateTime;


import com.example.User.enums.Gender;
import com.example.User.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
	    private Long id;
	    private String name;
	    private String email;
	    private Gender gender;
	    private LocalDate dateOfBirth;
	    private String phone;
	    private String profileImageUrl;    
	    private UserStatus status;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
}
