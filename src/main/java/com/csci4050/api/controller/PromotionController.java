package com.csci4050.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csci4050.api.exception.PromotionNotFoundException;
import com.csci4050.api.model.Promotion;
import com.csci4050.api.service.EmailService;
import com.csci4050.api.service.PromotionService;
import com.csci4050.api.service.UserService;

@RestController
@RequestMapping( consumes = {"application/json"})
@CrossOrigin(origins="*")
public class PromotionController {
	
	@Autowired
	PromotionService promotionService;

	@Autowired
	UserService userService;

	EmailService emailService = EmailService.getInstance();
	
	@PostMapping("/api/promotion")
	public ResponseEntity<?> createPromotion(@RequestBody Promotion promotion) {
		emailService.sendPromotion(promotion.getCode(), userService.getAllUsers());
		return new ResponseEntity<>(promotionService.createPromotion(promotion), HttpStatus.CREATED);
	}
	
	@PostMapping("/api/promotion/{promotionId}")
	public ResponseEntity<?> updatePromotion(@RequestBody Promotion promotion, 
			@PathVariable(name = "promotionId") Long unitId) throws PromotionNotFoundException {
		return new ResponseEntity<>(promotionService.updatePromotion(promotion), HttpStatus.OK);
	}

}
