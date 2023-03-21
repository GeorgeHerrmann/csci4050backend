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
import com.csci4050.api.model.User;
import com.csci4050.api.service.DataValidationService;
import com.csci4050.api.service.EmailService;
import com.csci4050.api.service.SessionKeyService;
import com.csci4050.api.service.UserService;


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
    public ResponseEntity<User> getUser(@PathVariable(value = "user") String user) throws UserNotFoundException {
        User u = userService.getUser(user);
        u.setPassword(dataValidationService.decryptString(u.getPassword()));
        u.getPayments().forEach(card ->  {
            card.setCardNumber(dataValidationService.decryptString(card.getCardNumber()));
            card.setName(dataValidationService.decryptString(card.getName()));
        });
       return new ResponseEntity<User>(u, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestParam String email, String password) throws UserNotFoundException {
            User user = userService.getUser(email);
            user.setPassword(dataValidationService.decryptString(user.getPassword()));
            user.getPayments().forEach(card ->  {
                card.setCardNumber(dataValidationService.decryptString(card.getCardNumber()));
                card.setName(dataValidationService.decryptString(card.getName()));
            });
            //emailService.sendEmail("gvhmann@gmail.com", "test email", "test body");
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
            user.setPassword(dataValidationService.encryptString(user.getPassword()));
            return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Invalid email", HttpStatus.BAD_REQUEST);
        }
    }

   
    @PostMapping("/user/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException, UserUpdateException {
        user.setPassword(dataValidationService.encryptString(user.getPassword()));
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
    }
    
    @DeleteMapping("user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
    	userService.deleteUser(userId);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user/{userId}/credentials")
    public ResponseEntity<?> updatePassword(@RequestBody Password password) throws UserNotFoundException {
        password.setPassword(dataValidationService.encryptString(password.getPassword()));
    	userService.updatePassword(password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
