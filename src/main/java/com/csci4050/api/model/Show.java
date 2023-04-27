package com.csci4050.api.model;


import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "movie_show")
public class Show {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "movie_id", referencedColumnName = "id")
	private Movie movie;
	
	@Column(name = "show_start")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp showStart;
	
	@Column(name = "show_end")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp showEnd;
	
	@ManyToOne
	@JoinColumn(name = "show_room_id", referencedColumnName = "id")
	private ShowRoom showRoom;

}
