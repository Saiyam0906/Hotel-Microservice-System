package com.example.Hotel.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Hotel.Entity.Hotel;
import com.example.Hotel.Exception.BadRequestException;
import com.example.Hotel.Exception.DuplicateResourceException;
import com.example.Hotel.Exception.ResourceNotFoundException;
import com.example.Hotel.Interface.HotelInterface;
import com.example.Hotel.Repository.HotelRepository;
import com.example.Hotel.dto.Request.HotelRequestDTO;
import com.example.Hotel.dto.Response.HotelResponseDto;
import com.example.Hotel.dto.update.HotelUpdateDTO;
import com.example.Hotel.mapper.MapStruct.HotelMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HotelService implements HotelInterface{
	
	private final HotelRepository hotelRepository;
	private final HotelMapper hotelMapper;

	@Override
    public HotelResponseDto saveHotel(HotelRequestDTO requestDTO) {

                boolean alreadyExists = hotelRepository
                .existsByNameAndLocation(requestDTO.getName(), requestDTO.getLocation());
        if (alreadyExists) {
            throw new DuplicateResourceException(
                "Hotel '" + requestDTO.getName() + 
                "' already exists in location '" + requestDTO.getLocation() + "'"
            );
        }

        Hotel hotel = hotelMapper.toEntity(requestDTO);
        Hotel savedHotel = hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(savedHotel);
    }

    @Override
    public HotelResponseDto getHotelById(Long id) {

        
        if (id <= 0) {
            throw new BadRequestException("Hotel ID must be a positive number");
        }

        
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", id));

        return hotelMapper.toResponseDTO(hotel);
    }

    @Override
    public List<HotelResponseDto> getAllHotels() {

        List<HotelResponseDto> hotels = hotelRepository.findAll()
                .stream()
                .map(hotelMapper::toResponseDTO)
                .toList();

       
        if (hotels.isEmpty()) {
            throw new ResourceNotFoundException("Hotels", "database", "no records found");
        }

        return hotels;
    }

    @Override
    public HotelResponseDto updateHotel(Long id, HotelUpdateDTO updateDTO) {

     
        if (id <= 0) {
            throw new BadRequestException("Hotel ID must be a positive number");
        }

      
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel", "id", id));

      
        if (updateDTO.getName() != null && updateDTO.getLocation() != null) {
            boolean alreadyExists = hotelRepository
                    .existsByNameAndLocation(updateDTO.getName(), updateDTO.getLocation());
            if (alreadyExists) {
                throw new DuplicateResourceException(
                    "Hotel '" + updateDTO.getName() + 
                    "' already exists in location '" + updateDTO.getLocation() + "'"
                );
            }
        }

        hotelMapper.updateHotelFromDto(updateDTO, hotel);
        Hotel updatedHotel = hotelRepository.save(hotel);
        return hotelMapper.toResponseDTO(updatedHotel);
    }

    @Override
    public void deleteHotel(Long id) {

        
        if (id <= 0) {
            throw new BadRequestException("Hotel ID must be a positive number");
        }

        
        if (!hotelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Hotel", "id", id);
        }

        hotelRepository.deleteById(id);
    }
}
