package com.csci4050.api.exception;

@SuppressWarnings("serial")
public class TheatreNotFoundException extends Exception {
	
	public TheatreNotFoundException(Long id) {
		super("Theatre " + id + "could not be found");
	}

}
