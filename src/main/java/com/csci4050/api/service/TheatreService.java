package com.csci4050.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci4050.api.model.ShowRoom;
import com.csci4050.api.model.Theatre;
import com.csci4050.api.repository.ShowRoomRepository;
import com.csci4050.api.repository.TheatreRepository;

import jakarta.transaction.Transactional;

@Service
public class TheatreService {
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	@Autowired
	private ShowRoomRepository showRoomRepository;

	@Transactional
	public Theatre addTheatre(Theatre theatre) {
		return theatreRepository.save(theatre);
	}
	
	@Transactional
	public ShowRoom addShowRoom(ShowRoom showRoom) {
		return showRoomRepository.save(showRoom);
	}
}
