package com.csci4050.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private PaymentAddress address;
	
	@Column(name = "cvv", updatable = false)
	private int cvv;
	
	@Column(name = "card_number", updatable = false)
	private String cardNumber;
	
	@Column(name = "name", updatable = false)
	private String name;
	
	@Column(name = "user_id", updatable = false)
	private int userId;
	
	@Column(name = "status", updatable = false)
	private STATUS status;
	
	public enum STATUS {
		PENDING
	}

}
