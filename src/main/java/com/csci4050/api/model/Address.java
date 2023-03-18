package com.csci4050.api.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment_address")
public class Address {

	@Id
	private Long paymentId;
	@Column(name = "street", updatable = false)
	private String street;
	@Column(name = "state", updatable = false)
	private String state;
	@Column(name = "zipcode", updatable = false)
	private String zipcode;
	@Column(name = "country", updatable = false)
	private String country;
	
	@OneToOne
    @MapsId
    @JoinColumn(name = "payment_id")
	private Payment payment;
}
