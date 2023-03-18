package com.csci4050.user;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csci4050.user.data.Address;
import com.csci4050.user.data.PaymentCard;
import com.csci4050.user.data.UserProfile;
import com.csci4050.user.data.UserStatus;

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
        Address temp = new Address("test", "test", "test", "test", "test");
        List<PaymentCard> card = List.of(new PaymentCard("test", "test", "test", "test"));
        return new UserProfile("test", "test", "test", "test", "test", temp, card, UserStatus.ACTIVE, false);
    }

    /**
     * Logs a user in.
     * @param email
     * @param password
     * @return
     */
    @GetMapping("/api/login")
    public String login(String email, String password) {
        return "example JWT token";
    }

    @GetMapping("/api/verifySession")
    public boolean verifySession(String token) {
        return true;
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
    public String register(String firstname, String lastname, String email, String password) {
        return "success";
    }

    @GetMapping("/api/getAddress")
    public @ResponseBody Address getAddress(String id) {
        return new Address("test", "test", "test", "test", "test");
    }

    @GetMapping("/api/getCards")
    public @ResponseBody List<PaymentCard> getCards(String id) {
        return List.of(new PaymentCard("test", "test", "test", "test"));
    }

    /**
     * Updates a user's name.
     * @param firstname
     * @param lastname
     * @return
     */
    @PostMapping("/api/updateName")
    public String updateName(String id, String firstname, String lastname) {
        return "success";
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
    public String updateAddress(String id, String street, String city, String state, String zip, String country) {
        return "success";
    }

    /**
     * Updates a user's password.
     * @param password
     * @return
     */
    @PostMapping("/api/updatePassword")
    public String updatePassword(String id, String password) {
        return "success";
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
        return "success";
    }

    /**
     * Removes a credit card from a user's account.
     * @param userId
     * @param cardNumber
     * @return
     */
    @PostMapping("/api/removeCard")
    public String removeCard(String userId, String cardNumber) {
        return "success";
    }
}
