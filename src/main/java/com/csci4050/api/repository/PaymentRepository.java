package com.csci4050.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci4050.api.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	public Boolean existsByIdAndUserIdAndCardNumber(Long id, Long userId, String cardNumber);

	public Boolean existsByUserId(Long userId);
}
