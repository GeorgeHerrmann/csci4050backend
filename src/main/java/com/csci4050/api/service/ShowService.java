package com.csci4050.api.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci4050.api.exception.ShowCreationException;
import com.csci4050.api.exception.ShowNotFoundException;
import com.csci4050.api.model.Show;
import com.csci4050.api.repository.ShowRepository;

import jakarta.transaction.Transactional;

@Service
public class ShowService {
	
	@Autowired
	ShowRepository showRepository;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Transactional
	public Show addShow(Show show) throws ShowCreationException {
		
		if (!showRepository.findConflictingShows(show.getShowStart(), show.getShowEnd(), show.getShowRoom().getId()).isEmpty()) {
			throw new ShowCreationException();
		}
		
		return show = showRepository.save(show);
	}
	
	@Transactional
	public Show updateShow(Show show) throws ShowNotFoundException {
		if (!showRepository.existsById(show.getId())) {
			throw new ShowNotFoundException(show.getId());
		}

		return showRepository.save(show);
	}
	
	@Transactional
	public Show getShow(Long id) throws ShowNotFoundException {
		if (!showRepository.existsById(id)) {
			throw new ShowNotFoundException(id);
		}
		return showRepository.findById(id).get();
	}
	
	@Transactional
	public void deleteShow(Long id) throws ShowNotFoundException {
		if (!showRepository.existsById(id)) {
			throw new ShowNotFoundException(id);
		}
		showRepository.deleteById(id);
	}
	
	@Transactional
	public List<Show> getUpcomingShowsByMovie(Long movieId, Instant date) {
		return showRepository.getByMovieIdAndShowStartGreaterThan(movieId, Timestamp.from(date));
	}

}
