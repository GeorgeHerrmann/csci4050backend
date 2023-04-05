package com.csci4050.api.service;

import java.util.List;

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
	
	DataValidationService dataValidationService = new DataValidationService();

	@Transactional
	public User createUser(User user) throws UserCreationException {
		if (!dataValidationService.isValidEmail(user.getEmail())) {
            throw new UserCreationException("Invalid email");
        }
		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserCreationException("username", user.getUsername());
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserCreationException("email", user.getEmail());
			
		}
		if (userRepository.existsByPhone(user.getPhone())) {
			throw new UserCreationException("phone", user.getPhone());
		}
		user.setPassword(dataValidationService.encryptString(user.getPassword()));
		return userRepository.save(user);
	}
	
	@Transactional
	public User updateUser(User user) throws UserNotFoundException, UserUpdateException {
		if (!userRepository.existsById(user.getId())) {
			throw new UserNotFoundException(user.getId() + "");
		} 

		if (userRepository.existsByIdIsNotAndEmail(user.getId(), user.getEmail())) {
			throw new UserUpdateException("email", user.getEmail());
		}
		if (userRepository.existsByIdIsNotAndUsername(user.getId(), user.getUsername())) {
			throw new UserUpdateException("username", user.getUsername());
		}
		if (userRepository.existsByIdIsNotAndPhone(user.getId(), user.getPhone())) {
			throw new UserUpdateException("phone", user.getPhone());
		}
		dataValidationService.encryptString(user.getPassword());
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
		password.setPassword(dataValidationService.encryptString(password.getPassword()));
		passwordRepository.save(password);
		
	}
	
	@Transactional
	public User getUser(String user) throws UserNotFoundException {
		if (userRepository.existsByUsername(user)) {
			return userRepository.findByUsername(user);	
		}
		if (userRepository.existsByEmail(user)) {
			return userRepository.findByEmail(user);
		}
		throw new UserNotFoundException(user);
		
	}

	@Transactional
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
}
