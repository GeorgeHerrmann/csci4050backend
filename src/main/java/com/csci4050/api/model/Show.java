package com.csci4050.api.model;


import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "show")
public class Show {
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@Column(name = "show_start")
	private Timestamp showStart;
	
	@Column(name = "show_end")
	private Timestamp showEnd;
	
	@ManyToOne
	@JoinColumn(name = "show_room_id", referencedColumnName = "id")
	private ShowRoom showRoom;

}
