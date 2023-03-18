package com.csci4050.api.exception;

import lombok.Getter;

@Getter
public class UserUpdateException extends Exception {

private String message;
	
	public UserUpdateException(String field, String value) {
		this.message = "Could Not Update User: The " + field + " " + value + " is associated with another account";
	}
}
