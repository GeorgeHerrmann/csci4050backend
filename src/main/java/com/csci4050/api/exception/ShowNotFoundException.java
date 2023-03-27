package com.csci4050.api.exception;

@SuppressWarnings("serial")
public class ShowNotFoundException extends Exception {
	
	public ShowNotFoundException(Long id) {
		super("Show " + id + "could not be found");
	}

}
