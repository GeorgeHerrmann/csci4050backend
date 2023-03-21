package com.csci4050.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csci4050.api.exception.UserCreationException;
import com.csci4050.api.exception.UserNotFoundException;
import com.csci4050.api.exception.UserUpdateException;
import com.csci4050.api.model.JWTResponse;
import com.csci4050.api.model.Password;
import com.csci4050.api.model.UserResponse;
import com.csci4050.api.model.User;
import com.csci4050.api.service.DataValidationService;
import com.csci4050.api.service.EmailService;
import com.csci4050.api.service.SessionKeyService;
import com.csci4050.api.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(path = {"/api"}, consumes = {"application/json"})
@CrossOrigin(origins="*")
public class UserController {
    SessionKeyService keyService = new SessionKeyService();
    DataValidationService dataValidationService = new DataValidationService();

    @Autowired
    EmailService emailService;
	@Autowired
	UserService userService;
    
    @GetMapping("/user/{user}")
    public ResponseEntity<?> getUser(@PathVariable(value = "user") String user) throws UserNotFoundException {
        try {
            User u = userService.getUser(user);
            u.setPassword(dataValidationService.decryptString(u.getPassword()));
            u.getPayments().forEach(card ->  {
                card.setCardNumber(dataValidationService.decryptString(card.getCardNumber()));
                card.setName(dataValidationService.decryptString(card.getName()));
            });
            return new ResponseEntity<User>(u, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<UserResponse>(new UserResponse("User not found", null), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestParam String email, String password) throws UserNotFoundException {
            User user = userService.getUser(email);
            user.setPassword(dataValidationService.decryptString(user.getPassword()));
            user.getPayments().forEach(card ->  {
                card.setCardNumber(dataValidationService.decryptString(card.getCardNumber()));
                card.setName(dataValidationService.decryptString(card.getName()));
            });
            if (user.getPassword().equals(password)) {
                String key = keyService.createSessionKey();
                return new ResponseEntity<JWTResponse>(new JWTResponse(key, "true", user), HttpStatus.OK);
            } else {
                return new ResponseEntity<JWTResponse>(new JWTResponse("", "false", null), HttpStatus.UNAUTHORIZED);
            }
    }

    @GetMapping("/verifySession")
    public ResponseEntity<Boolean> verifySession(String token) {
        if (!token.isEmpty() && keyService.verifySessionKey(token)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody User user) throws UserCreationException {
        if (dataValidationService.isValidEmail(user.getEmail())) {
            emailService.sendEmail(user.getEmail(), "Welcome to Cine City", "Thank you for registering with Cine City!" +
                    " Your email has been verified and your account has been created, you can now log in.");
            user.setPassword(dataValidationService.encryptString(user.getPassword()));
            return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Invalid email", HttpStatus.BAD_REQUEST);
        }
    }

   
    @PostMapping("/user/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException, UserUpdateException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        user.setPassword(dataValidationService.encryptString(user.getPassword()));
        user.setId(userService.getUser(user.getEmail()).getId());
        emailService.sendEmail(user.getEmail(), "Cine City Account Update", "Your account has been updated, if you did not make this change please contact us immediately.");
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
    }
    
    @DeleteMapping("user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
    	userService.deleteUser(userId);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user/{userId}/credentials")
    public ResponseEntity<UserResponse> updatePassword(@RequestBody Password password, @RequestParam String email) throws UserNotFoundException {
        password.setPassword(dataValidationService.encryptString(password.getPassword()));
        try {
    	    userService.updatePassword(password);
            System.out.println(email);
            emailService.sendEmail(email, "Cine City Password Update", "Your password has been updated, if you did not make this change please contact us immediately.");
        } catch (UserNotFoundException e) {
            return new ResponseEntity<UserResponse>(new UserResponse("User does not exist", null), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserResponse>(new UserResponse("Success", null), HttpStatus.OK);
    }

    @PostMapping("/user/{email}/forgot")
    public ResponseEntity<UserResponse> forgotPassword(@RequestParam String email) {
        emailService.sendEmail(email, "Cine City Password Reset",
        "You have requested a password reset, please click the link below to reset your password. \n" +
        "http://localhost:3000/resetpassword");
        return new ResponseEntity<UserResponse>(new UserResponse("Success", null), HttpStatus.OK);
    }

}
