package com.csci4050.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	
	@Column(name = "order_id")
	Long orderId;
	
	@Column(name = "type")
	String type;
	
	@Column(name = "price")
	Float price;
	
	@Column(name = "show_id")
	Long showId;
	
	@ManyToOne
	@JoinColumn(name = "seat_id", referencedColumnName = "id")
	Seat seat;
}
