package com.example.Hotel.mapper.MapStruct;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.Hotel.Entity.Hotel;
import com.example.Hotel.dto.Request.HotelRequestDTO;
import com.example.Hotel.dto.Response.HotelResponseDto;
import com.example.Hotel.dto.update.HotelUpdateDTO;

import jakarta.persistence.MappedSuperclass;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HotelMapper {
	Hotel toEntity(HotelRequestDTO requestDTO);

    // Entity → ResponseDTO
    HotelResponseDto toResponseDTO(Hotel hotel);

   
    void updateHotelFromDto(HotelUpdateDTO updateDTO, @MappingTarget Hotel hotel);
}
