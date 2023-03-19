package com.csci4050.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class Password {

	@Id
	@Column(name = "id")
	private Long userId;
	
	@Column(name ="password")
	private String password;
}
