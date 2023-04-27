package com.csci4050.api.controller;

import java.sql.Timestamp;

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
import com.csci4050.api.exception.ShowNotFoundException;
import com.csci4050.api.model.Show;
import com.csci4050.api.service.ShowService;

@RestController
@RequestMapping(path = {"/api"}, consumes = {"application/json"})
@CrossOrigin(origins="*")
public class ShowController {
	@Autowired
	ShowService showService;
	
	 @PostMapping(value = "/show")
	 public ResponseEntity<?> createShow(@RequestBody Show show) throws ShowCreationException {
		 return new ResponseEntity<>(showService.addShow(show), HttpStatus.CREATED);
		 
	 }
	 
	 @PostMapping(value = "/show/{showId}")
	 public ResponseEntity<?> updateShow(@RequestBody Show show) throws ShowNotFoundException {
		 return new ResponseEntity<>(showService.updateShow(show), HttpStatus.OK);
		 
	 }
	 
	 @DeleteMapping(value = "/show/{showId}")
	 public ResponseEntity<?> deleteShow(@PathVariable(name = "showId") Long showId) throws ShowNotFoundException {
		 showService.deleteShow(showId);
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 }
	 
	 @GetMapping(value = "/show/{showId}")
	 public ResponseEntity<?> getShow(@PathVariable(name = "showId") Long showId) throws ShowNotFoundException {
		 return new ResponseEntity<>(showService.getShow(showId), HttpStatus.OK);
	 }
	 
	 @GetMapping(value = "/movie/{movieId}/shows/upcoming/{date}")
	 public ResponseEntity<?> getUpcomingShowsForMovie(@PathVariable(name = "movieId") Long movieId, 
			 @PathVariable(name = "date") String date) {
		 return new ResponseEntity<>(showService.getUpcomingShowsByMovie(movieId, Timestamp.valueOf(date)), HttpStatus.OK); 
	 }
	 
	 @GetMapping(value = "/movie/{movieId}/show/{showId}/seats")
	 public ResponseEntity<?> getUpcomingShowsForMovie(@PathVariable(name = "movieId") Long movieId, 
			 @PathVariable(name = "showId") Long showId) {
		 return new ResponseEntity<>(showService.getAvailableSeats(showId), HttpStatus.OK); 
	 }

}
