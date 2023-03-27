package com.csci4050.api.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csci4050.api.model.Show;
import com.csci4050.api.service.ShowService;

@RestController
@RequestMapping(path = {"/api"}, consumes = {"application/json"})
@CrossOrigin(origins="*")
public class ShowController {
	@Autowired
	ShowService showService;
	
	 @PostMapping(value = "/show")
	 public void createShow(@RequestBody Show show) {
		 showService.addShow(show);
	 }
	 
	 @PostMapping(value = "/show/{showId}")
	 public void updateShow(@RequestBody Show show) {
		 showService.updateShow(show);
		 
	 }
	 
	 @DeleteMapping(value = "/show/{showId}")
	 public void deleteShow(@PathVariable(name = "showId") Long showId) {
		 showService.deleteShow(showId);
	 }
	 
	 @GetMapping(value = "/show/{showId}")
	 public void getShow(@PathVariable(name = "showId") Long showId) {
		 showService.getShow(showId);
	 }
	 
	 @GetMapping(value = "/movie/{movieId}/shows/upcoming/{date}")
	 public void getUpcomingShowsForMovie(@PathVariable(name = "movieId") Long movieId, 
			 @PathVariable(name = "date") Instant date) {
		 showService.getUpcomingShowsByMovie(movieId, date);
		 
	 }

}
