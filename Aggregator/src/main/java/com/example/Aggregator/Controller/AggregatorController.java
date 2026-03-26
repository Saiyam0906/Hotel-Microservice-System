package com.example.Aggregator.Controller;

import java.util.List;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Aggregator.Dto.ApiResponse;
import com.example.Aggregator.Dto.FullUserProfileDto;
import com.example.Aggregator.Dto.HotelResponseDto;
import com.example.Aggregator.Dto.HotelWithRatingsDto;
import com.example.Aggregator.Dto.RatingResponseDto;
import com.example.Aggregator.Dto.UserDashboardDto;
import com.example.Aggregator.Service.AggregatorService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/aggregate")
@RequiredArgsConstructor
public class AggregatorController {
	
	private final AggregatorService aggregatorService;

    
    @GetMapping("/users/{userId}/full-profile")
    public ResponseEntity<ApiResponse<FullUserProfileDto>> getFullUserProfile(
            @PathVariable Long userId) {
        log.info("GET /aggregate/users/{}/full-profile", userId);
        FullUserProfileDto response = aggregatorService.getFullUserProfile(userId);
        return ResponseEntity.ok(ApiResponse.success("Full user profile retrieved", response));
    }

   
    @GetMapping("/hotels/{hotelId}/with-ratings")
    public ResponseEntity<ApiResponse<HotelWithRatingsDto>> getHotelWithRatings(
            @PathVariable Long hotelId) {
        log.info("GET /aggregate/hotels/{}/with-ratings", hotelId);
        HotelWithRatingsDto response = aggregatorService.getHotelWithRatings(hotelId);
        return ResponseEntity.ok(ApiResponse.success("Hotel with ratings retrieved", response));
    }

    
    @GetMapping("/users/{userId}/dashboard")
    public ResponseEntity<ApiResponse<UserDashboardDto>> getUserDashboard(
            @PathVariable Long userId) {
        log.info("GET /aggregate/users/{}/dashboard", userId);
        UserDashboardDto response = aggregatorService.getUserDashboard(userId);
        return ResponseEntity.ok(ApiResponse.success("User dashboard retrieved", response));
    }

}
