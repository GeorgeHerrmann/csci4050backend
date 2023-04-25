package com.csci4050.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci4050.api.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{

	List<Seat> findByShowRoomId(Long showRoomId);
}
