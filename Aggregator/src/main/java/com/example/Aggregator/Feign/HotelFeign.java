package com.example.Aggregator.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Aggregator.Dto.ApiResponse;
import com.example.Aggregator.Dto.HotelResponseDto;

@FeignClient(name = "HOTEL")
public interface HotelFeign {
	
	@GetMapping("/hotels/{id}")
    ApiResponse<HotelResponseDto> getHotelById(@PathVariable("id") Long id);

    // matches GET /hotels in HotelController
    @GetMapping("/hotels")
    ApiResponse<List<HotelResponseDto>> getAllHotels();


}
