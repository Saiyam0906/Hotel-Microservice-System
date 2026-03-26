package com.example.Aggregator.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Aggregator.Dto.ApiResponse;
import com.example.Aggregator.Dto.RatingResponseDto;

@FeignClient(name = "RATING")
public interface RatingFeign {
	
	
	@GetMapping("/api/ratings/user/{userId}")
    ApiResponse<List<RatingResponseDto>> getRatingsByUserId(@PathVariable("userId") Long userId);

    @GetMapping("/api/ratings")  
    ApiResponse<List<RatingResponseDto>> getAllRatings();

}
