package com.csci4050.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csci4050.api.data.Address;
import com.csci4050.api.data.PaymentCard;
import com.csci4050.api.data.UserProfile;
import com.csci4050.api.data.UserStatus;
import com.csci4050.api.exception.UserCreationException;
import com.csci4050.api.exception.UserNotFoundException;
import com.csci4050.api.exception.UserUpdateException;
import com.csci4050.api.model.User;
import com.csci4050.api.service.UserService;

/**
 * Primary API controller for the backend.
 * This class will be used to handle API requests between the frontend and the
 * backend. Connections to the database will be made through a service class in
 * the database package (implemented by Tsemaye).
 * 
 * Implementator: George Herrmann
 */
@RestController
@RequestMapping(path = {"/api"}, consumes = {"application/json"})
@CrossOrigin(origins="*")
public class UserController {
	@Autowired
	UserService userService;
    
//    @GetMapping("/api/getUser")
//    public @ResponseBody UserProfile getUser(String id) {
//        Address temp = new Address("test", "test", "test", "test", "test");
//        List<PaymentCard> card = List.of(new PaymentCard("test", "test", "test", "test"));
//        return new UserProfile();
//    }

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

    @GetMapping("/api/getAddress")
    public @ResponseBody Address getAddress(String id) {
        return new Address("test", "test", "test", "test", "test");
    }

    @GetMapping("/api/getCards")
    public @ResponseBody List<PaymentCard> getCards(String id) {
        return List.of(new PaymentCard("test", "test", "test", "test"));
    }

    
    @PostMapping("/user/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException, UserUpdateException {
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
    }

    
   
    @PostMapping("/api/updatePassword")
    public String updatePassword(String id, String password) {
        return "success";
    }

    
    @PostMapping("/api/addCard")
    public String addCard(String userId, String cardNumber, String cardName, String cardExpDate, String cardCvv) {
        return "success";
    }

  
    @PostMapping("/api/removeCard")
    public String removeCard(String userId, String cardNumber) {
        return "success";
    }
}
