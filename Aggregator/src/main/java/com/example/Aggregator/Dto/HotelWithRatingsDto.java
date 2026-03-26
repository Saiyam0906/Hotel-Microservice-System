package com.example.Aggregator.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelWithRatingsDto {
	 private HotelResponseDto hotel;
	    private List<RatingResponseDto> ratings;
}
