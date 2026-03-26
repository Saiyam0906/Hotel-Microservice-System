package com.example.User.Response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
