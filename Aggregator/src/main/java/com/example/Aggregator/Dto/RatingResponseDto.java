package com.example.Aggregator.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponseDto {
	private Long ratingId;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;
}
