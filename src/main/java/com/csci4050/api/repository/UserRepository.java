package com.csci4050.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci4050.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public Boolean existsByUsername(String username);
	public Boolean existsByIdIsNotAndUsername(Long id, String username);
	public Boolean existsByIdIsNotAndEmail(Long id, String username);
	public Boolean existsByIdIsNotAndPhone(Long id, String username);
	public Boolean existsByPhone(String phone);
	public Boolean existsByEmail(String email);
	public User findByUsername(String username);
	public User findByEmail(String email);

}
