package com.example.Rating.Interface;

import java.util.List;
import java.util.Optional;

import com.example.Rating.Entity.Rating;
import com.example.Rating.dto.Response.RatingResponseDTO;
import com.example.Rating.dto.Update.RatingUpdateDTO;
import com.example.Rating.dto.request.RatingRequestDTO;

public interface RatingInterface {

    RatingResponseDTO saveRating(RatingRequestDTO requestDTO);

    List<RatingResponseDTO> getAllRatings();

    RatingResponseDTO updateRating(Long ratingId, RatingUpdateDTO updateDTO);

    void deleteRating(Long ratingId);

    List<RatingResponseDTO> getRatingsByUserId(Long userId);
}
