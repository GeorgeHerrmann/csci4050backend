package com.csci4050.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csci4050.api.exception.MovieNotFoundException;
import com.csci4050.api.model.Movie;
import com.csci4050.api.service.MovieService;

@RestController
@RequestMapping(path = {"/api"}, consumes = {"application/json"})
@CrossOrigin(origins="*")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/movie")
	public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
		return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
	}
	
	@GetMapping("/movie/{movieId}")
	public ResponseEntity<?> getMovie(@PathVariable("movieId") Long movieId) throws MovieNotFoundException {
		return new ResponseEntity<>(movieService.getMovie(movieId), HttpStatus.OK);
	}
	
	@PostMapping("/movie/{movieId}")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie, 
			@PathVariable("movieId") Long movieId) throws MovieNotFoundException {
		return new ResponseEntity<>(movieService.updateMovie(movie), HttpStatus.OK);
	}

}
