package com.csci4050.api.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

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
	
	@Transactional
	public Show addShow(Show show) throws ShowCreationException {
		if (showRepository.existsByShowStartBetweenOrShowEndBetweenAndShowRoomId(
				show.getShowStart(), show.getShowEnd(), show.getShowStart(), show.getShowEnd(), show.getShowRoom().getId())) {
			throw new ShowCreationException();
		}
		return showRepository.save(show);	
	}
	
	@Transactional
	public Show updateShow(Show show) throws ShowNotFoundException {
		if (!showRepository.existsById(show.getId())) {
			throw new ShowNotFoundException(show.getId());
		}

		return showRepository.save(show);
	}
	
	@SuppressWarnings("deprecation")
	@Transactional
	public Show getShow(Long id) throws ShowNotFoundException {
		if (!showRepository.existsById(id)) {
			throw new ShowNotFoundException(id);
		}
		return showRepository.getById(id);
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
