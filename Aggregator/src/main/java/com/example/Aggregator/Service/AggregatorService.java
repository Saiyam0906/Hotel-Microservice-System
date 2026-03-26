package com.example.Aggregator.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.Aggregator.Dto.FullUserProfileDto;
import com.example.Aggregator.Dto.HotelResponseDto;
import com.example.Aggregator.Dto.HotelWithRatingsDto;
import com.example.Aggregator.Dto.RatingResponseDto;
import com.example.Aggregator.Dto.UserAddressResponseDto;
import com.example.Aggregator.Dto.UserDashboardDto;
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
public class AggregatorService {

	    private final UserFeign userFeign;
	    private final RatingFeign ratingFeign;
	    private final HotelFeign hotelFeign;
	    
	    private final AggeratorFeignService feignService;
	    
	    
	    @Cacheable(value = "fullProfileCache", key = "#userId")
	    public FullUserProfileDto getFullUserProfile(Long userId) {
	        log.info("Fetching full user profile for userId: {}", userId);
	        UserResponseDto user              = feignService.fetchUser(userId);
	        UserAddressResponseDto address    = feignService.fetchAddress(userId);
	        List<RatingResponseDto> ratings   = feignService.fetchRatingsByUser(userId);
	        return new FullUserProfileDto(user, address, ratings);
	    }
	    
	    
	    @Cacheable(value = "hotelWithRatingsCache", key = "#hotelId")
	    public HotelWithRatingsDto getHotelWithRatings(Long hotelId) {
	        log.info("Fetching hotel with ratings for hotelId: {}", hotelId);
	        HotelResponseDto hotel = feignService.fetchHotel(hotelId);
	        List<RatingResponseDto> ratings = feignService.fetchAllRatings()
	                .stream()
	                .filter(r -> r.getHotelId().equals(hotelId))
	                .collect(Collectors.toList());
	        return new HotelWithRatingsDto(hotel, ratings);
	    }
	  
	    @Cacheable(value = "userDashboardCache", key = "#userId")
	    public UserDashboardDto getUserDashboard(Long userId) {
	        log.info("Fetching dashboard for userId: {}", userId);
	        UserResponseDto user            = feignService.fetchUser(userId);
	        UserAddressResponseDto address  = feignService.fetchAddress(userId);
	        List<RatingResponseDto> ratings = feignService.fetchRatingsByUser(userId);
	        List<HotelResponseDto> hotels   = ratings.stream()
	                .map(r -> feignService.fetchHotel(r.getHotelId()))
	                .collect(Collectors.toList());
	        return new UserDashboardDto(user, address, ratings, hotels);
	    }
	    
}
