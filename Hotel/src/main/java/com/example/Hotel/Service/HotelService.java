package com.example.Hotel.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Hotel.Entity.Hotel;
import com.example.Hotel.Interface.HotelInterface;
import com.example.Hotel.Repository.HotelRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HotelService implements HotelInterface{
	
	private final HotelRepository hotelRepository;

	    @Override
	    public Hotel saveHotel(Hotel hotel) {
	        return hotelRepository.save(hotel);
	    }

	    @Override
	    public Optional<Hotel> getHotelById(Long id) {
	        return hotelRepository.findById(id);
	    }

	    @Override
	    public List<Hotel> getAllHotels() {
	        return hotelRepository.findAll();
	    }

	    @Override
	    public void deleteHotel(Long id) {
	        hotelRepository.deleteById(id);
	    }

}
