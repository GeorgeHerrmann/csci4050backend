package com.csci4050.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci4050.api.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	boolean existsByShowIdAndSeatId(Long showId, Long seatId);
	
	List<Ticket> findByShowId(Long showId);

}
