package com.csci4050.api.service;

import java.util.List;

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
	
	@Transactional
	public List<Movie> getAllUpcomingMovies() {
		return movieRepository.findByStatus("UPCOMING");
	}
	
	@Transactional
	public List<Movie> getAllMoviesNowPlaying() {
		return movieRepository.findByStatus("NOW_PLAYING");
	}
	
	
	@Transactional
	public List<Movie> getMoviesByStatusAndCategory(String status, String category) {
		if (status.equals("now-playing")) {
			status = "NOW_PLAYING";
		} else if (status.equals("upcoming")) {
			status = "UPCOMING";
		}
		return movieRepository.findByStatusAndCategory(status, category);
	}
	

}
