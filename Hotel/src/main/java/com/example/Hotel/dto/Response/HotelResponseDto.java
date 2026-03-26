package com.example.Hotel.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponseDto {
	    private Long id;
	    private String name;
	    private String location;
	    private String about;
}
