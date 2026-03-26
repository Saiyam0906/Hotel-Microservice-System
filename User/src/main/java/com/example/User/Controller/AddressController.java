package com.example.User.Controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.User.Interface.addressInterface;
import com.example.User.Request.UserAddressRequestDto;
import com.example.User.Response.ApiResponse;
import com.example.User.Response.UserAddressResponseDto;
import com.example.User.Update.UserAddressUpdateDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/users/{userId}/address")  
@RequiredArgsConstructor
@Validated
public class AddressController {
	
	
	private final addressInterface addressService;
	
	
	@PostMapping
    public ResponseEntity<ApiResponse<UserAddressResponseDto>> createAddress(
            @PathVariable @Positive(message = "User ID must be a positive number") Long userId,
            @Valid @RequestBody  UserAddressRequestDto requestDto) {

        log.info("POST /api/v1/users/{}/address - Creating address", userId);

        UserAddressResponseDto response = addressService.createAddress(userId, requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Address created successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<UserAddressResponseDto>> getAddressByUserId(
            @PathVariable @Positive(message = "User ID must be a positive number") Long userId) {

        log.info("GET /api/v1/users/{}/address - Fetching address", userId);

        UserAddressResponseDto response = addressService.getAddressByUserId(userId);

        return ResponseEntity.ok(ApiResponse.success("Address retrieved successfully", response));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserAddressResponseDto>> updateAddress(
            @PathVariable @Positive(message = "User ID must be a positive number") Long userId,
            @Valid @RequestBody UserAddressUpdateDto updateDto) {

        log.info("PUT /api/v1/users/{}/address - Updating address", userId);

        UserAddressResponseDto response = addressService.updateAddress(userId, updateDto);

        return ResponseEntity.ok(ApiResponse.success("Address updated successfully", response));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAddress(
            @PathVariable @Positive(message = "User ID must be a positive number") Long userId) {

        log.info("DELETE /api/v1/users/{}/address - Deleting address", userId);

        addressService.deleteAddress(userId);

        return ResponseEntity.noContent().build();         
    }
	

}
