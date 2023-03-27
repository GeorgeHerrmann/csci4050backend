package com.csci4050.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "show_room")
public class ShowRoom {

	@Id
	@Column(name ="id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "theatre_id")
	private Long theatreId;
}
