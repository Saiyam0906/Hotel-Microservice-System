package com.example.Hotel.Interface;

import java.util.List;
import java.util.Optional;

import com.example.Hotel.Entity.Hotel;

public interface HotelInterface {
	
	Hotel saveHotel(Hotel hotel);
	
	
	Optional<Hotel> getHotelById(Long id);
	
	List<Hotel> getAllHotels();
	
	void deleteHotel(Long id);
}
