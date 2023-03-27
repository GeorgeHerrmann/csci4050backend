package com.csci4050.api.exception;

@SuppressWarnings("serial")
public class ShowCreationException extends Exception {
	
	public ShowCreationException() {
		super("Show could not be created another show is booked during that time period");
	}

}
