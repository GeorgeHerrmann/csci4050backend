package com.csci4050.api.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "type")
	private TYPE type;

	@Column(name = "status")
	private STATUS status;

	@Column(name = "email")
	private String email;

	@Column(name = "password", updatable = false)
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "username")
	private String username;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "recieve_promotions")
	Boolean recievePromotions;

	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	private List<Payment> payments;

	private Address address;

	public enum STATUS {
		PENDING, ACTIVE, INACTIVE
	}

	public enum TYPE {
		CUSTOMER, ADMIN
	}

}
