package com.csci4050.api.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class UserCreationException extends Exception {
	
	private String message;
	
	public UserCreationException(String field, String value) {
		this.message = "Could Not Create User: The " + field + " " + value + " is associated with another account";
	}

}
