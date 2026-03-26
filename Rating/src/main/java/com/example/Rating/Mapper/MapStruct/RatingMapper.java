package com.example.Rating.Mapper.MapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.Rating.Entity.Rating;
import com.example.Rating.dto.Response.RatingResponseDTO;
import com.example.Rating.dto.Update.RatingUpdateDTO;
import com.example.Rating.dto.request.RatingRequestDTO;

@Mapper(componentModel = "spring")
public interface RatingMapper {
	
	Rating toEntity(RatingRequestDTO dto);
	
	RatingResponseDTO toResponseDTO(Rating rating);
	
	
	void updateRatingFromDto(RatingUpdateDTO dto, @MappingTarget Rating rating);

}
