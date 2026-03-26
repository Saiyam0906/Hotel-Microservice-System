package com.example.Rating.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Rating.Entity.Rating;
import com.example.Rating.Interface.RatingInterface;
import com.example.Rating.Service.RatingService;
import com.example.Rating.dto.Response.ApiResponse;
import com.example.Rating.dto.Response.RatingResponseDTO;
import com.example.Rating.dto.Update.RatingUpdateDTO;
import com.example.Rating.dto.request.RatingRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
	
	private final RatingInterface ratingService;
	
	
	@PostMapping
    public ResponseEntity<ApiResponse<RatingResponseDTO>> createRating(
            @RequestBody RatingRequestDTO requestDTO) {

        RatingResponseDTO rating = ratingService.saveRating(requestDTO);

        ApiResponse<RatingResponseDTO> response = ApiResponse.<RatingResponseDTO>builder()
                .success(true)
                .message("Rating created successfully")
                .data(rating)
                .build();

        return ResponseEntity.ok(response);
    }
	
	@GetMapping
    public ResponseEntity<ApiResponse<List<RatingResponseDTO>>> getAllRatings() {

        List<RatingResponseDTO> ratings = ratingService.getAllRatings();

        ApiResponse<List<RatingResponseDTO>> response = ApiResponse.<List<RatingResponseDTO>>builder()
                .success(true)
                .message("Ratings fetched successfully")
                .data(ratings)
                .build();

        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<RatingResponseDTO>>> getRatingsByUserId(
            @PathVariable Long userId) {

        List<RatingResponseDTO> ratings = ratingService.getRatingsByUserId(userId);

        ApiResponse<List<RatingResponseDTO>> response = ApiResponse.<List<RatingResponseDTO>>builder()
                .success(true)
                .message("User ratings fetched successfully")
                .data(ratings)
                .build();

        return ResponseEntity.ok(response);
    }
	
	@PutMapping("/{ratingId}")
    public ResponseEntity<ApiResponse<RatingResponseDTO>> updateRating(
            @PathVariable Long ratingId,
            @RequestBody RatingUpdateDTO updateDTO) {

        RatingResponseDTO updated = ratingService.updateRating(ratingId, updateDTO);

        ApiResponse<RatingResponseDTO> response = ApiResponse.<RatingResponseDTO>builder()
                .success(true)
                .message("Rating updated successfully")
                .data(updated)
                .build();

        return ResponseEntity.ok(response);
    }
	
	@DeleteMapping("/{ratingId}")
    public ResponseEntity<ApiResponse<Void>> deleteRating(@PathVariable Long ratingId) {

        ratingService.deleteRating(ratingId);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Rating deleted successfully")
                .data(null)
                .build();

        return ResponseEntity.ok(response);
    }


   

}
