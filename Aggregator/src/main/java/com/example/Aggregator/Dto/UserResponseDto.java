package com.example.Aggregator.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.Aggregator.Enums.Gender;
import com.example.Aggregator.Enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
