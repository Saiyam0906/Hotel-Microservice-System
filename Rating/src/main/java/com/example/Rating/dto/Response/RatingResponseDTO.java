package com.example.Rating.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RatingResponseDTO {
	
	  private Long ratingId;

	    private Long userId;

	    private Long hotelId;

	    private int rating;

	    private String feedback;

}
