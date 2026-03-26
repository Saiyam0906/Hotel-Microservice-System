package com.example.Hotel.Exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.Hotel.dto.Response.ErrorResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err -> fieldErrors.put(
                        err.getField(),
                        err.getDefaultMessage()
                ));

        log.warn("Validation failed: {}", fieldErrors);

        ErrorResponseDto body = ErrorResponseDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("One or more fields are invalid")
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(LocalDateTime.now())
                .validationErrors(fieldErrors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(
            ResourceNotFoundException ex, WebRequest request) {

        log.warn("Resource not found: {}", ex.getMessage());

        return buildResponse(
                HttpStatus.NOT_FOUND,
                "Not Found",
                ex.getMessage(),
                request
        );
    }
	
	 @ExceptionHandler(DuplicateResourceException.class)
	    public ResponseEntity<ErrorResponseDto> handleDuplicateResource(
	            DuplicateResourceException ex, WebRequest request) {

	        log.warn("Duplicate resource: {}", ex.getMessage());

	        return buildResponse(
	                HttpStatus.CONFLICT,
	                "Conflict",
	                ex.getMessage(),
	                request
	        );
	    }
	 
	 @ExceptionHandler(BadRequestException.class)
	    public ResponseEntity<ErrorResponseDto> handleBadRequest(
	            BadRequestException ex, WebRequest request) {

	        log.warn("Bad request: {}", ex.getMessage());

	        return buildResponse(
	                HttpStatus.BAD_REQUEST,
	                "Bad Request",
	                ex.getMessage(),
	                request
	        );
	    }
	 
	 @ExceptionHandler(HttpMessageNotReadableException.class)
	    public ResponseEntity<ErrorResponseDto> handleUnreadableMessage(
	            HttpMessageNotReadableException ex, WebRequest request) {

	        log.warn("Malformed JSON: {}", ex.getMessage());

	        return buildResponse(
	                HttpStatus.BAD_REQUEST,
	                "Malformed JSON",
	                "Request body is missing or has invalid format",
	                request
	        );
	    }
	 
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponseDto> handleGenericException(
	            Exception ex, WebRequest request) {

	        log.error("Unexpected error occurred: ", ex);

	        return buildResponse(
	                HttpStatus.INTERNAL_SERVER_ERROR,
	                "Internal Server Error",
	                "An unexpected error occurred. Please try again later.",
	                request
	        );
	    }
	
	
	private ResponseEntity<ErrorResponseDto> buildResponse(
            HttpStatus status, String error, String message, WebRequest request) {

        ErrorResponseDto body = ErrorResponseDto.builder()
                .status(status.value())
                .error(error)
                .message(message)
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(body);
    }
}
