package com.csci4050.api.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class UserNotFoundException  extends Exception {

	private String message;
	
	public UserNotFoundException(String id) {
		this.message = "Could Not Complete Request: User " + id + " Does Not Exist";
	}

}
