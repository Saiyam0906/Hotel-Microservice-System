package com.example.Hotel.dto.update;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelUpdateDTO {
	
	@Size(min = 2, max = 100, message = "Hotel name must be between 2 and 100 characters")
    private String name;

    @Size(min = 2, max = 200, message = "Location must be between 2 and 200 characters")
    private String location;

    @Size(max = 1000, message = "About section must not exceed 1000 characters")
    private String about;
}
