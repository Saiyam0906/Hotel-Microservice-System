package com.example.Rating.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Rating.Entity.Rating;
import com.example.Rating.Interface.RatingInterface;
import com.example.Rating.Repository.RatingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingService implements RatingInterface{
	
	private final RatingRepository ratingRepository; // Inject repository

	@Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

   

    @Override
    public Rating updateRating(Rating rating) {
        // save() works for update if the ID exists
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }

	@Override
	public List<Rating> getRatingsByUserId(Long userId) {
		
		return ratingRepository.findByUserId(userId);
	}
    

}
