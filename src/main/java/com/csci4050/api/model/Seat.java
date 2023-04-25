package com.csci4050.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "seat")
public class Seat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	@Column(name = "seat_row")
	String row;
	
	@Column(name = "seat_number")
	String seatNumber;
	
	@JsonIgnore
	@Column(name = "show_room_id")
	Long showRoomId;

}
