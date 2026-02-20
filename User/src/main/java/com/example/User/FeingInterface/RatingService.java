package com.example.User.FeingInterface;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.User.Entity.Rating;

@FeignClient(name="RATING")
public interface RatingService {
	
	@GetMapping("/api/ratings/users/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable("userId") Long userId);
}
