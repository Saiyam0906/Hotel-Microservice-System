package com.example.Aggregator.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Aggregator.Dto.ApiResponse;
import com.example.Aggregator.Dto.UserAddressResponseDto;
import com.example.Aggregator.Dto.UserResponseDto;

@FeignClient(name="USER-SERVICE")
public interface UserFeign {
	
	@GetMapping("/api/v1/users/{id}")
    ApiResponse<UserResponseDto> getUserById(@PathVariable("id") Long id);
	
	
	@GetMapping("/api/v1/users/{userId}/address")
    ApiResponse<UserAddressResponseDto> getAddressByUserId(@PathVariable("userId") Long userId);

}
