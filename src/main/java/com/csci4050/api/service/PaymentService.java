package com.csci4050.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci4050.api.exception.PaymentException;
import com.csci4050.api.model.Payment;
import com.csci4050.api.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	@Transactional
	public void deletePayment(Payment payment) throws PaymentException {
		if (paymentRepository.existsByIdAndUserIdAndCardNumber(payment.getId(), payment.getUserId(), payment.getCardNumber())) {
			throw new PaymentException("Payment could not be deleted as because payment does not exist or could not be found");
		}
		paymentRepository.deleteById(payment.getId());
	}
	
	@Transactional
	public void addPayment(Payment payment) throws PaymentException {
		if (paymentRepository.existsByUserId(payment.getUserId())) {
			throw new PaymentException("Payment could not be created as user does not exists");
		}
		paymentRepository.save(payment);
	}
}
