package com.csci4050.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci4050.api.model.PaymentAddress;

@Repository
public interface AddressRepository extends JpaRepository<PaymentAddress, Long>{

}
