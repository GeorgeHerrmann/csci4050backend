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
import com.csci4050.api.service.DataValidationService;
import com.csci4050.api.service.PaymentService;

@RestController
@RequestMapping(path = {"/api/user/{userId}/payment"}, consumes = {"application/json"})
@CrossOrigin(origins="*")
public class PaymentController {
	DataValidationService validationService = new DataValidationService();
	@Autowired
	PaymentService paymentService;
	
	@DeleteMapping
	public ResponseEntity<?> deletePayment(@RequestBody Payment payment) throws PaymentException {
		payment.setCardNumber(validationService.encryptString(payment.getCardNumber()));
		paymentService.deletePayment(payment);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping
	public ResponseEntity<?> addPayment(@RequestBody Payment payment) throws PaymentException {
		payment.setCardNumber(validationService.encryptString(payment.getCardNumber()));
		paymentService.addPayment(payment);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
