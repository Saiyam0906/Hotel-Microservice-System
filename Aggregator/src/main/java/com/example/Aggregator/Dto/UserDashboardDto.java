package com.example.Aggregator.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDashboardDto {
	private UserResponseDto user;
    private UserAddressResponseDto address;
    private List<RatingResponseDto> ratings;
    private List<HotelResponseDto> hotels;
}
