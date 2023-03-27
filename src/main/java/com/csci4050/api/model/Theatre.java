package com.csci4050.api.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "theatre")
public class Theatre {
	
	@Id
	@Column(name ="id")
	private String id;
	
	@OneToMany(mappedBy = "theatreId", cascade = CascadeType.ALL)
	private List<ShowRoom> showRooms;
	
	private Address address;
	
	

}
