package com.csci4050.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci4050.api.exception.MovieNotFoundException;
import com.csci4050.api.model.Movie;
import com.csci4050.api.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Transactional
	public Movie createMovie(Movie movie) {
		return movieRepository.save(movie);
	}
	
	@Transactional
	public Movie getMovie(Long id) throws MovieNotFoundException {
		if (!movieRepository.existsById(id)) {
			throw new MovieNotFoundException(id);
		}
		return movieRepository.findById(id).get();
	}
	
	@Transactional
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		if (!movieRepository.existsById(movie.getId())) {
			throw new MovieNotFoundException(movie.getId());
		}
		return movieRepository.save(movie);
	}
	

}
