package com.csci4050.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci4050.api.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	public List<Movie> findByStatus(String status);
	
	public List<Movie> findByStatusAndCategory(String status, String category);

}
