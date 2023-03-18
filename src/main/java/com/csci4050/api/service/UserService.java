package com.csci4050.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci4050.api.exception.UserCreationException;
import com.csci4050.api.exception.UserNotFoundException;
import com.csci4050.api.exception.UserUpdateException;
import com.csci4050.api.model.User;
import com.csci4050.api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Transactional
	public User createUser(User user) throws UserCreationException {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserCreationException("username", user.getUsername());
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserCreationException("email", user.getEmail());
			
		}
		if (userRepository.existsByPhone(user.getPhone())) {
			throw new UserCreationException("phone", user.getPhone());
		}
			
		return userRepository.save(user);
	}
	
	@Transactional
	public User updateUser(User user) throws UserNotFoundException, UserUpdateException {
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserUpdateException("username", user.getUsername());
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserUpdateException("email", user.getEmail());
			
		}
		if (userRepository.existsByPhone(user.getPhone())) {
			throw new UserUpdateException("phone", user.getPhone());
		}
		if (!userRepository.existsByUsername(user.getUsername())) {
			throw new UserNotFoundException(user.getUsername());
		} 
		
		return userRepository.save(user);
		
		
	}
	
	
}
