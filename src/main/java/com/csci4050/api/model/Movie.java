package com.csci4050.api.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "trailer")
	private String trailer;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "rating")
	private float rating;
	
	@Column(name = "poster")
	private String poster;
	
	@Column(name = "actor")
	private String actors;
	
	@Column(name = "director")
	private String director;
	
}
