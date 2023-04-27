package com.csci4050.api.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci4050.api.model.Order;
import com.csci4050.api.model.Promotion;
import com.csci4050.api.model.Ticket;
import com.csci4050.api.repository.OrderRepository;
import com.csci4050.api.repository.PromotionRepository;
import com.csci4050.api.repository.TicketRepository;
import com.csci4050.api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	PromotionRepository promotionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Transactional
	public Order createOrder(Order order) throws Exception {
		areSeatsAvailable(order);
		order.setSubtotal(calculateSubTotal(order));
		order.setTotal(calculateTotal(order));
		order.setCreated(Timestamp.from(Instant.now()));
		List<Ticket> tickets = order.getTickets();
		order = orderRepository.save(order);
		
		for (Ticket ticket: tickets) {
			ticket.setOrderId(order.getId());
		}
		order.setTickets(ticketRepository.saveAll(tickets));
		return order;
	}
	
	@Transactional
	public List<Order> getOrders(Long userId) {
		if (!userRepository.existsById(userId)) {
			
		}
		return orderRepository.findByUserId(userId);
	}
	
	private Float calculateTotal(Order order) {
		if (order.getPromotionId() == null) {
			return order.getSubtotal();
		}
		Promotion promotion = promotionRepository.findById(order.getPromotionId()).get();
		
		return order.getSubtotal() - order.getSubtotal() * (promotion.getDiscount()/100);
	
		
	}
	
	private Float calculateSubTotal(Order order) {
		float subTotal = 0;
		for (Ticket ticket : order.getTickets()) {
			subTotal += ticket.getPrice();
		}
		return subTotal;
		
	}
	
	private boolean areSeatsAvailable(Order order) throws Exception {
		for (Ticket ticket : order.getTickets()) {
			if (ticketRepository.existsByShowIdAndSeatId(order.getShow().getId(), 
					ticket.getSeat().getId())) {
				throw new Exception();
			}
		}
		
		return true;
	}

}
