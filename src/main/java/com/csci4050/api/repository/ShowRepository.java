package com.csci4050.api.repository;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci4050.api.model.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

	public Boolean existsByShowStartBetweenOrShowEndBetweenAndShowRoomId(
			Timestamp startStart, Timestamp startEnd, Timestamp endStart, Timestamp endEnd, Long showRoomId);
	
	public List<Show> getByMovieIdAndShowStartGreaterThan(Long movieId, Timestamp showStart);
}
