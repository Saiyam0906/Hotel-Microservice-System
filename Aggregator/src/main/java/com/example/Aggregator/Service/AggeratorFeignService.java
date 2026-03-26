package com.example.Aggregator.Service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Aggregator.Dto.HotelResponseDto;
import com.example.Aggregator.Dto.RatingResponseDto;
import com.example.Aggregator.Dto.UserAddressResponseDto;
import com.example.Aggregator.Dto.UserResponseDto;
import com.example.Aggregator.Feign.HotelFeign;
import com.example.Aggregator.Feign.RatingFeign;
import com.example.Aggregator.Feign.UserFeign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AggeratorFeignService {
	
	    private final UserFeign userFeign;
	    private final RatingFeign ratingFeign;
	    private final HotelFeign hotelFeign;
	    
	    
	    @CircuitBreaker(name = "userService", fallbackMethod = "fetchUserFallback")
	    public UserResponseDto fetchUser(Long userId) {
	        return userFeign.getUserById(userId).getData();
	    }

	    @CircuitBreaker(name = "userService", fallbackMethod = "fetchAddressFallback")
	    public UserAddressResponseDto fetchAddress(Long userId) {
	        return userFeign.getAddressByUserId(userId).getData();
	    }

	    @CircuitBreaker(name = "ratingService", fallbackMethod = "fetchRatingsByUserFallback")
	    public List<RatingResponseDto> fetchRatingsByUser(Long userId) {
	        return ratingFeign.getRatingsByUserId(userId).getData();
	    }

	    @CircuitBreaker(name = "ratingService", fallbackMethod = "fetchAllRatingsFallback")
	    public List<RatingResponseDto> fetchAllRatings() {
	        return ratingFeign.getAllRatings().getData();
	    }

	    @CircuitBreaker(name = "hotelService", fallbackMethod = "fetchHotelFallback")
	    public HotelResponseDto fetchHotel(Long hotelId) {
	        return hotelFeign.getHotelById(hotelId).getData();
	    }

	  
	    public UserResponseDto fetchUserFallback(Long userId, Throwable t) {
	        log.error("User service DOWN for userId: {}. Reason: {}", userId, t.getMessage());
	        throw new RuntimeException("User service unavailable for userId: " + userId, t);
	    }

	    public UserAddressResponseDto fetchAddressFallback(Long userId, Throwable t) {
	        log.warn("Address unavailable for userId: {}. Returning null.", userId);
	        return null;
	    }

	    public List<RatingResponseDto> fetchRatingsByUserFallback(Long userId, Throwable t) {
	        log.warn("Rating service unavailable for userId: {}. Returning empty list.", userId);
	        return Collections.emptyList();
	    }

	    public List<RatingResponseDto> fetchAllRatingsFallback(Throwable t) {
	        log.warn("Rating service unavailable (fetchAll). Returning empty list.");
	        return Collections.emptyList();
	    }

	    public HotelResponseDto fetchHotelFallback(Long hotelId, Throwable t) {
	        log.warn("Hotel service unavailable for hotelId: {}. Returning empty shell.", hotelId);
	        return new HotelResponseDto();
	    }

}
