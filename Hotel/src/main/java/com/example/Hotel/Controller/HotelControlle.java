package com.example.Hotel.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Hotel.Entity.Hotel;
import com.example.Hotel.Interface.HotelInterface;
import com.example.Hotel.dto.Request.HotelRequestDTO;
import com.example.Hotel.dto.Response.ApiResponse;
import com.example.Hotel.dto.Response.HotelResponseDto;
import com.example.Hotel.dto.update.HotelUpdateDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor 
public class HotelControlle {
	
	private final HotelInterface hotelService;
	

	  @PostMapping
	    public ResponseEntity<ApiResponse<HotelResponseDto>> saveHotel(
	            @RequestBody @Valid HotelRequestDTO requestDTO) {
	        HotelResponseDto data = hotelService.saveHotel(requestDTO);
	        ApiResponse<HotelResponseDto> response = ApiResponse.<HotelResponseDto>builder()
	                .success(true)
	                .message("Hotel created successfully")
	                .data(data)
	                .build();
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ApiResponse<HotelResponseDto>> getHotelById(
	            @PathVariable Long id) {
	        HotelResponseDto data = hotelService.getHotelById(id);
	        ApiResponse<HotelResponseDto> response = ApiResponse.<HotelResponseDto>builder()
	                .success(true)
	                .message("Hotel fetched successfully")
	                .data(data)
	                .build();
	        return ResponseEntity.ok(response);
	    }

	    @GetMapping
	    public ResponseEntity<ApiResponse<List<HotelResponseDto>>> getAllHotels() {
	        List<HotelResponseDto> data = hotelService.getAllHotels();
	        ApiResponse<List<HotelResponseDto>> response = ApiResponse.<List<HotelResponseDto>>builder()
	                .success(true)
	                .message("Hotels fetched successfully")
	                .data(data)
	                .build();
	        return ResponseEntity.ok(response);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ApiResponse<HotelResponseDto>> updateHotel(
	            @PathVariable Long id,
	            @RequestBody @Valid HotelUpdateDTO updateDTO) {
	        HotelResponseDto data = hotelService.updateHotel(id, updateDTO);
	        ApiResponse<HotelResponseDto> response = ApiResponse.<HotelResponseDto>builder()
	                .success(true)
	                .message("Hotel updated successfully")
	                .data(data)
	                .build();
	        return ResponseEntity.ok(response);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<ApiResponse<Void>> deleteHotel(
	            @PathVariable Long id) {
	        hotelService.deleteHotel(id);
	        ApiResponse<Void> response = ApiResponse.<Void>builder()
	                .success(true)
	                .message("Hotel deleted successfully")
	                .data(null)
	                .build();
	        return ResponseEntity.ok(response);
	    }
	}
   
	
	


