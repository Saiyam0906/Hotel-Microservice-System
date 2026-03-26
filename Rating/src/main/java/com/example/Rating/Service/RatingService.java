package com.example.Rating.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Rating.Entity.Rating;
import com.example.Rating.Exception.BadRequestException;
import com.example.Rating.Exception.DuplicateResourceException;
import com.example.Rating.Exception.ResourceNotFoundException;
import com.example.Rating.Interface.RatingInterface;
import com.example.Rating.Mapper.MapStruct.RatingMapper;
import com.example.Rating.Repository.RatingRepository;
import com.example.Rating.dto.Response.RatingResponseDTO;
import com.example.Rating.dto.Update.RatingUpdateDTO;
import com.example.Rating.dto.request.RatingRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingService implements RatingInterface{
	
	private final RatingRepository ratingRepository;
	private final RatingMapper ratingMapper;
	
	
	
	@Override
	public RatingResponseDTO saveRating(RatingRequestDTO requestDTO) {
		
		boolean alreadyRated = ratingRepository
                .existsByUserIdAndHotelId(requestDTO.getUserId(), requestDTO.getHotelId());
		
        if (alreadyRated) {
            throw new DuplicateResourceException(
                "User " + requestDTO.getUserId() + 
                " has already rated hotel " + requestDTO.getHotelId()
            );
        }
		Rating rating = ratingMapper.toEntity(requestDTO);

        Rating savedRating = ratingRepository.save(rating);

        return ratingMapper.toResponseDTO(savedRating);
	
	}
	
	 @Override
	    public List<RatingResponseDTO> getAllRatings() {

		 List<RatingResponseDTO> ratings = ratingRepository.findAll()
	                .stream()
	                .map(ratingMapper::toResponseDTO)
	                .toList();

	        // ResourceNotFoundException — no ratings exist at all
	        if (ratings.isEmpty()) {
	            throw new ResourceNotFoundException("Ratings", "database", "no records found");
	        }

	        return ratings;
	    }

	    @Override
	    public RatingResponseDTO updateRating(Long ratingId, RatingUpdateDTO updateDTO) {

	    	if (ratingId <= 0) {
	            throw new BadRequestException("Rating ID must be a positive number");
	        }

	       
	        Rating rating = ratingRepository.findById(ratingId)
	                .orElseThrow(() -> new ResourceNotFoundException("Rating", "id", ratingId));

	        ratingMapper.updateRatingFromDto(updateDTO, rating);

	        Rating updatedRating = ratingRepository.save(rating);

	        return ratingMapper.toResponseDTO(updatedRating);
	    }

	    @Override
	    public void deleteRating(Long ratingId) {
	    	
	    	 if (ratingId <= 0) {
	             throw new BadRequestException("Rating ID must be a positive number");
	         }

	         // ResourceNotFoundException — can't delete what doesn't exist
	         if (!ratingRepository.existsById(ratingId)) {
	             throw new ResourceNotFoundException("Rating", "id", ratingId);
	         }

	        ratingRepository.deleteById(ratingId);
	    }

	    @Override
	    public List<RatingResponseDTO> getRatingsByUserId(Long userId) {
	    	
	    	  if (userId <= 0) {
	              throw new BadRequestException("User ID must be a positive number");
	          }

	    	  List<RatingResponseDTO> ratings = ratingRepository.findByUserId(userId)
	                  .stream()
	                  .map(ratingMapper::toResponseDTO)
	                  .toList();

	          // ResourceNotFoundException — no ratings found for this user
	          if (ratings.isEmpty()) {
	              throw new ResourceNotFoundException("Ratings", "userId", userId);
	          }

	          return ratings;
	    }

	

}
