package com.csci4050.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci4050.api.model.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long>{
	
	public List<Order> findByUserId(Long userId);

}
