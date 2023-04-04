package com.csci4050.api.exception;

@SuppressWarnings("serial")
public class PromotionNotFoundException extends Exception {
	
	public PromotionNotFoundException(Long id) {
		super("Promotion " + id + "could not be found");
	}

}
