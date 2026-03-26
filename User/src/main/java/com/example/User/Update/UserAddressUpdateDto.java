package com.example.User.Update;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserAddressUpdateDto {
	
	@Size(max = 100, message = "Street must not exceed 100 characters")
    private String street;

    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @Size(max = 50, message = "State must not exceed 50 characters")
    private String state;

    @Size(max = 50, message = "Country must not exceed 50 characters")
    private String country;

    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
    private String pincode;

}
