package com.csci4050.api.repository;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csci4050.api.model.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
	
	public List<Show> getByMovieIdAndShowStartGreaterThan(Long movieId, Timestamp showStart);
	
	@Query(value = "select * from movie_show where (show_start BETWEEN ?1 AND ?2) and "
			+ "(show_end BETWEEN ?1 AND ?2) and show_room_id = ?3",
			nativeQuery = true)
	public List<Show> findConflictingShows(Timestamp start, Timestamp end, Long showRoomId);
}
