package com.example.Aggregator.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class HotelResponseDto {
	private Long id;
	private String name;
	private String location;
	private String about;
}
