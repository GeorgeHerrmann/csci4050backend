package com.csci4050.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csci4050.backend.data.UserProfile;

/**
 * Primary API controller for the backend.
 * This class will be used to handle API requests between the frontend and the
 * backend. Connections to the database will be made through a service class in
 * the database package (implemented by Tsemaye).
 * 
 * Implementator: George Herrmann
 */
@RestController
@CrossOrigin(origins="*")
public class ApiController {
    /**
     * Returns a user's profile.
     * @param id
     * @return
     */
    @GetMapping("/api/getUser")
    public @ResponseBody UserProfile getUser(String id) {
        return null;
    }

    /**
     * Logs a user in.
     * @param email
     * @param password
     * @return
     */
    @GetMapping("/api/login")
    public @ResponseBody UserProfile login(String email, String password) {
        return null;
    }

    /**
     * Registers a new user.
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param country
     * @return
     */
    @GetMapping("/api/register")
    public @ResponseBody UserProfile register(String firstname, String lastname, String email, String password, String street, String city, String state, String zip, String country) {
        return null;
    }

    /**
     * Updates a user's name.
     * @param firstname
     * @param lastname
     * @return
     */
    @PostMapping("/api/updateName")
    public String updateName(String firstname, String lastname) {
        return null;
    }

    /**
     * Updates a user's address.
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param country
     * @return
     */
    @PostMapping("/api/updateAddress")
    public String updateAddress(String street, String city, String state, String zip, String country) {
        return null;
    }

    /**
     * Updates a user's password.
     * @param password
     * @return
     */
    @PostMapping("/api/updatePassword")
    public String updatePassword(String password) {
        return null;
    }

    /**
     * Adds a credit card to a user's account.
     * @param userId
     * @param cardNumber
     * @param cardName
     * @param cardExpDate
     * @param cardCvv
     * @return
     */
    @PostMapping("/api/addCard")
    public String addCard(String userId, String cardNumber, String cardName, String cardExpDate, String cardCvv) {
        return null;
    }

    /**
     * Removes a credit card from a user's account.
     * @param userId
     * @param cardNumber
     * @return
     */
    @PostMapping("/api/removeCard")
    public String removeCard(String userId, String cardNumber) {
        return null;
    }
}
