package com.example.Aggregator.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAddressResponseDto {

	    private Long id;
	    private String street;
	    private String city;
	    private String state;
	    private String country;
	    private String pincode;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
}
