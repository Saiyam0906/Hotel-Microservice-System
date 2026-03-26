package com.example.Rating.dto.Update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RatingUpdateDTO {

    private int rating;

    private String feedback;
}
