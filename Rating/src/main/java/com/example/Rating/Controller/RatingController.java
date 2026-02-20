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
import com.example.Rating.Service.RatingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
	
	private final RatingService ratingService;

    // Create a rating
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody
    		Rating rating) {
        Rating savedRating = ratingService.saveRating(rating);
        return ResponseEntity.ok(savedRating);
    }

    // Get all ratings
    @GetMapping
    
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }

    // Get rating by ID
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable Long userId) {

        List<Rating> ratings = ratingService.getRatingsByUserId(userId);
        return ResponseEntity.ok(ratings);
    }

    // Update a rating
    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable("id") Long id, @RequestBody Rating rating) {
        rating.setRatingId(id); // Ensure the ID is set for update
        Rating updatedRating = ratingService.updateRating(rating);
        return ResponseEntity.ok(updatedRating);
    }

    // Delete a rating
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable("id") Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

}
