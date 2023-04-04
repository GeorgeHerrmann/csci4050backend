package com.csci4050.api.exception;

@SuppressWarnings("serial")
public class MovieNotFoundException extends Exception {
	
	public MovieNotFoundException(Long id) {
		super("Movie " + id + "could not be found");
	}

}
