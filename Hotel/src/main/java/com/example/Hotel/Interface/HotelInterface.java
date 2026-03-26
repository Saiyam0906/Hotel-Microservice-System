package com.example.Hotel.Interface;

import java.util.List;
import java.util.Optional;

import com.example.Hotel.Entity.Hotel;
import com.example.Hotel.dto.Request.HotelRequestDTO;
import com.example.Hotel.dto.Response.HotelResponseDto;
import com.example.Hotel.dto.update.HotelUpdateDTO;

public interface HotelInterface {
	
	 HotelResponseDto saveHotel(HotelRequestDTO requestDTO);
	    HotelResponseDto getHotelById(Long id);
	    List<HotelResponseDto> getAllHotels();
	    HotelResponseDto updateHotel(Long id, HotelUpdateDTO updateDTO);
	    void deleteHotel(Long id);
}
