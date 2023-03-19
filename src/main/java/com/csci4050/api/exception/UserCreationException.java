package com.csci4050.api.exception;

import lombok.Getter;

@SuppressWarnings("serial")
public class UserCreationException extends Exception {
	
	public UserCreationException(String field, String value) {
		super("Could Not Create User: The " + field + " " + value + " is associated with another account");
	}

}
