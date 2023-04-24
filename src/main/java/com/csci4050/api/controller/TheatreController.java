package com.csci4050.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csci4050.api.exception.ShowCreationException;
import com.csci4050.api.exception.TheatreNotFoundException;
import com.csci4050.api.model.Seat;
import com.csci4050.api.model.ShowRoom;
import com.csci4050.api.model.Theatre;
import com.csci4050.api.service.TheatreService;

@RestController
@RequestMapping(path = {"/api"}, consumes = {"application/json"})
@CrossOrigin(origins="*")
public class TheatreController {
	
	@Autowired
	TheatreService theatreService;
	
	@PostMapping(value = "/theatre")
	public ResponseEntity<?> addTheatre(@RequestBody Theatre theatre) throws ShowCreationException {
		 return new ResponseEntity<>(theatreService.addTheatre(theatre), HttpStatus.CREATED);
		 
	 }
	
	@GetMapping(value = "/theatre/{id}")
	public ResponseEntity<?> getTheatre(@PathVariable("id") Long theatreId) throws TheatreNotFoundException {
		return new ResponseEntity<>(theatreService.getTheatre(theatreId), HttpStatus.OK);
	 }
	
	@DeleteMapping(value = "/theatre/{theatreId}")
	public ResponseEntity<?> deleteTheatre(@PathVariable("theatreId") Long theatreId) throws TheatreNotFoundException {
		theatreService.deleteTheatre(theatreId);
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 }
	
	@PostMapping(value = "/theatre/{theatreId}/show-room")
	public ResponseEntity<?> addShowRoom(@RequestBody ShowRoom showRoom) {
		 return new ResponseEntity<>(theatreService.addShowRoom(showRoom), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/theatre/{theatreId}/show-room/{showRoomId}/seats")
	public ResponseEntity<?> addSeats(@RequestBody List<Seat> seats) {
		 return new ResponseEntity<>(theatreService.addSeats(seats), HttpStatus.CREATED);
	}

}
