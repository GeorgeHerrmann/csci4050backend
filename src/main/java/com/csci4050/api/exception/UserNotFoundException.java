package com.csci4050.api.exception;

import lombok.Getter;

@SuppressWarnings("serial")
public class UserNotFoundException  extends Exception {
	
	public UserNotFoundException(String id) {
		super("Could Not Complete Request: User " + id + " Does Not Exist");
	}

}
