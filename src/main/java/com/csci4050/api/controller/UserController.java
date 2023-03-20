package com.csci4050.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csci4050.api.exception.UserCreationException;
import com.csci4050.api.exception.UserNotFoundException;
import com.csci4050.api.exception.UserUpdateException;
import com.csci4050.api.model.Password;
import com.csci4050.api.model.User;
import com.csci4050.api.service.UserService;


@RestController
@RequestMapping(path = {"/api"}, consumes = {"application/json"})
@CrossOrigin(origins="*")
public class UserController {
	@Autowired
	UserService userService;
    
    @GetMapping("/user/{user}")
    public ResponseEntity<User> getUser(@Param(value = "user") String user) throws UserNotFoundException {
    	return new ResponseEntity<User>(userService.getUser(user), HttpStatus.OK);
    }

    @GetMapping("/api/login")
    public String login(String email, String password) {
        return "example JWT token";
    }

    @GetMapping("/api/verifySession")
    public boolean verifySession(String token) {
        return true;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody User user) throws UserCreationException {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

   
    @PostMapping("/user/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException, UserUpdateException {
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
    }
    
    @DeleteMapping("user/{userId}")
    public ResponseEntity<?> deleteUser(@Param(value = "userId") Long userId) throws UserNotFoundException {
    	userService.deleteUser(userId);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user/{userId}/credentials")
    public ResponseEntity<?> updatePassword(@RequestBody Password password) throws UserNotFoundException {
    	userService.updatePassword(password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
