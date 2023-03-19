package com.csci4050.api.exception;

import lombok.Getter;

public class UserUpdateException extends Exception {

	
	public UserUpdateException(String field, String value) {
		super("Could Not Update User: The " + field + " " + value + " is associated with another account");
	}
}
