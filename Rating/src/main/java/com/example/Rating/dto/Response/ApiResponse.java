package com.example.Rating.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
	
	private boolean success;

    private String message;

    private T data;

}
