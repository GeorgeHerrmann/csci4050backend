package com.csci4050.api.model;



import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {

	@Column(name = "street")
	private String street;
	@Column(name = "state")
	private String state;
	@Column(name = "zipcode")
	private String zipcode;
	@Column(name = "country")
	private String country;
}
