package com.csci4050.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci4050.api.model.Theatre;


@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

}
