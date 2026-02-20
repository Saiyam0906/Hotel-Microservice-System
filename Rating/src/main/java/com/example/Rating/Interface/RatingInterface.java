package com.example.Rating.Interface;

import java.util.List;
import java.util.Optional;

import com.example.Rating.Entity.Rating;

public interface RatingInterface {

	 Rating saveRating(Rating rating);

	   
	    List<Rating> getAllRatings();

	   
	    List<Rating> getRatingsByUserId(Long userId);

	    
	    
	    Rating updateRating(Rating rating);
	   
	    void deleteRating(Long ratingId);
}
