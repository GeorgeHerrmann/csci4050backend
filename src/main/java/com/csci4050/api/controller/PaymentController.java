package com.csci4050.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csci4050.api.exception.PaymentException;
import com.csci4050.api.model.Payment;
import com.csci4050.api.model.UserResponse;
import com.csci4050.api.service.DataValidationService;
import com.csci4050.api.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = {"/api/user/{userId}/payment"}, consumes = {"application/json"})
@CrossOrigin(origins="*")
public class PaymentController {
	DataValidationService validationService = new DataValidationService();
	@Autowired
	PaymentService paymentService;
	
	@DeleteMapping("/delete")
	public ResponseEntity<UserResponse> deletePayment(@RequestBody Payment payment) throws PaymentException {
		payment.setCardNumber(validationService.encryptString(payment.getCardNumber()));
		paymentService.deletePayment(payment);
		return new ResponseEntity<>(new UserResponse("Success", null), HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<UserResponse> addPayment(@RequestBody Payment payment) throws PaymentException {
		payment.setCardNumber(validationService.encryptString(payment.getCardNumber()));
		payment.setName(validationService.encryptString(payment.getName()));
		try {
			paymentService.addPayment(payment);
		} catch (PaymentException e) {
			return new ResponseEntity<>(new UserResponse("Failure", null), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(new UserResponse("Success", null), HttpStatus.OK);
		
	}

}
