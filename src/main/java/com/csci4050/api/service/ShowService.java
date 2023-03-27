package com.csci4050.api.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci4050.api.model.Show;
import com.csci4050.api.repository.ShowRepository;

import jakarta.transaction.Transactional;

@Service
public class ShowService {
	
	@Autowired
	ShowRepository showRepository;
	
	@Transactional
	public Show addShow(Show show) {
		if (showRepository.existsByShowStartBetweenOrShowEndBetweenAndShowRoomId(
				show.getShowStart(), show.getShowEnd(), show.getShowStart(), show.getShowEnd(), show.getShowRoom().getId())) {
			
		}
		return showRepository.save(show);	
	}
	
	@Transactional
	public Show updateShow(Show show) {
		if (!showRepository.existsById(show.getId())) {
			
		}

		return showRepository.save(show);
	}
	
	@Transactional
	public Show getShow(Long id) {
		if (!showRepository.existsById(id)) {
			
		}
		return showRepository.getById(id);
	}
	
	@Transactional
	public void deleteShow(Long id) {
		if (!showRepository.existsById(id)) {
			
		}
		showRepository.deleteById(id);
	}
	
	@Transactional
	public List<Show> getUpcomingShowsByMovie(Long movieId, Instant date) {
		return showRepository.getByMovieIdAndShowStartGreaterThan(movieId, Timestamp.from(date));
	}

}
