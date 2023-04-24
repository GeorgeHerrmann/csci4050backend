package com.csci4050.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci4050.api.exception.TheatreNotFoundException;
import com.csci4050.api.model.Seat;
import com.csci4050.api.model.ShowRoom;
import com.csci4050.api.model.Theatre;
import com.csci4050.api.repository.SeatRepository;
import com.csci4050.api.repository.ShowRoomRepository;
import com.csci4050.api.repository.TheatreRepository;

import jakarta.transaction.Transactional;

@Service
public class TheatreService {
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	@Autowired
	private ShowRoomRepository showRoomRepository;
	
	@Autowired
	private SeatRepository seatRepository;

	@Transactional
	public Theatre addTheatre(Theatre theatre) {
		return theatreRepository.save(theatre);
	}
	
	@Transactional
	public void deleteTheatre(Long id) throws TheatreNotFoundException {
		if (!theatreRepository.existsById(id)) {
			throw new TheatreNotFoundException(id);
		}
		theatreRepository.deleteById(id);
	}
	
	@Transactional
	public Theatre getTheatre(Long id) throws TheatreNotFoundException {
		if (!theatreRepository.existsById(id)) {
			throw new TheatreNotFoundException(id);
		}
		return theatreRepository.findById(id).get();
	}
	
	@Transactional
	public ShowRoom addShowRoom(ShowRoom showRoom) {
		return showRoomRepository.save(showRoom);
	}
	
	@Transactional
	public ShowRoom deleteShowRoom(ShowRoom showRoom) {
		return showRoomRepository.save(showRoom);
	}
	
	@Transactional
	public List<Seat> addSeats(List<Seat> seats) {
		return seatRepository.saveAll(seats);
	}
}
