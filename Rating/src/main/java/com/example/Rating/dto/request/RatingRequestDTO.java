package com.example.Rating.dto.request;




import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RatingRequestDTO {
	
	@NotNull(message = "User ID is required")
	private Long userId;

	@NotNull(message = "Hotel ID is required")
    private Long hotelId;

	@Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private int rating;

	 @NotBlank(message = "Feedback cannot be blank")
	 @Size(min = 10, max = 500, message = "Feedback must be between 10 and 500 characters")
    private String feedback;

}
