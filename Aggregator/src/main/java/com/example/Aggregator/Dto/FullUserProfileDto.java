package com.example.Aggregator.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullUserProfileDto {
	  private UserResponseDto user;
	  private UserAddressResponseDto address;
	  private List<RatingResponseDto> ratings;
}
