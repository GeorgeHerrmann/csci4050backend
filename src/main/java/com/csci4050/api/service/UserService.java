package com.csci4050.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci4050.api.exception.UserCreationException;
import com.csci4050.api.exception.UserNotFoundException;
import com.csci4050.api.exception.UserUpdateException;
import com.csci4050.api.model.Password;
import com.csci4050.api.model.User;
import com.csci4050.api.repository.PasswordRepository;
import com.csci4050.api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordRepository passwordRepository;

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
		if (!userRepository.existsByUsername(user.getUsername())) {
			throw new UserNotFoundException(user.getUsername());
		} 
		
		return userRepository.save(user);
		
	}
	
	@Transactional
	public void deleteUser(Long id) throws UserNotFoundException {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException(id + "");
		} 
		
		userRepository.deleteById(id);
	}
	
	@Transactional
	public void updatePassword(Password password) throws UserNotFoundException {
		if (!userRepository.existsById(password.getUserId())) {
			throw new UserNotFoundException(password.getUserId() + "");
		} 
		passwordRepository.save(password);
		
	}
	
	public User getUser(String user) throws UserNotFoundException {
		if (userRepository.existsByUsername(user)) {
			return userRepository.findByUsername(user);	
		}
		if (userRepository.existsByEmail(user)) {
			return userRepository.findByEmail(user);
		}
		throw new UserNotFoundException(user);
		
	}
	
}
