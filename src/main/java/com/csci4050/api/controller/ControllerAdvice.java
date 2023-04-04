package com.csci4050.api.controller;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.csci4050.api.exception.PaymentException;
import com.csci4050.api.exception.ShowCreationException;
import com.csci4050.api.exception.ShowNotFoundException;
import com.csci4050.api.exception.UserCreationException;
import com.csci4050.api.exception.UserNotFoundException;
import com.csci4050.api.exception.UserUpdateException;


@RestControllerAdvice
public class ControllerAdvice {
	
	Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	
	@ExceptionHandler(value = {UserCreationException.class, UserUpdateException.class, PaymentException.class, ShowCreationException.class})
	public ResponseEntity<?> validation(Exception e) {
		
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {UserNotFoundException.class, ShowNotFoundException.class, ShowNotFoundException.class})
	public ResponseEntity<?> notFound(UserNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	

}
