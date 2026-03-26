package com.example.User.Exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleResourceExists(ResourceAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    } 
	
	
	@ExceptionHandler(ResourseNotFound.class)
    public ResponseEntity<String> ResourseNotFound(ResourceAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    } 
	
	


} 
 