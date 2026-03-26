package com.example.Hotel.Exception;

public class BadRequestException extends RuntimeException{
	
	public BadRequestException(String message) {
        super(message);
    }

}
