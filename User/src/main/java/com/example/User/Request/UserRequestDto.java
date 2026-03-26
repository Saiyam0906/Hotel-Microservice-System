package com.example.User.Request;

import java.time.LocalDate;



import com.example.User.enums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UserRequestDto {
	
	    @NotBlank(message = "Name is required")
	    @Size(max = 50)
	    private String name;

	    @Email(message = "Invalid email format")
	    @NotBlank
	    private String email;

	    private Gender gender;

	    @Past(message = "DOB must be in the past")
	    private LocalDate dateOfBirth;

	    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
	    private String phone;

	    private String profileImageUrl;

	   

}
