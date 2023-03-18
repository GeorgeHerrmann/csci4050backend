package com.csci4050.user.services;

import com.csci4050.user.data.Address;
import com.csci4050.user.data.PaymentCard;


/**
 * Create a class which features a basic service which can validate data from the frontend.
 * Things like, is the email string a valid email, are the address fields in the Address object valid, etc.
 * There are a few methods that need to be implemented, but add more if we need it.
 * 
 * Implementator: Kartikey
 */
public class DataValidationService {
    public boolean isValidAddress(Address address) {
        throw new UnsupportedOperationException();
    }

    public boolean isValidCard(PaymentCard card) {
        throw new UnsupportedOperationException();
    }

    public boolean isValidEmail(String email) {
        throw new UnsupportedOperationException();
    }

    /**
     * Encrypts a string using a hashing algorithm.
     * You can use an outside library for this, it might be easier.
     * 
     * @param input The string to encrypt.
     * @return The encrypted string.
     */
    public String encryptString(String input) {
        throw new UnsupportedOperationException();
    }

    /**
     * Decrypts a string using a hashing algorithm.
     * You can use an outside library for this, it might be easier.
     * 
     * @param input The string to decrypt.
     * @return The decrypted string.
     */
    public String decryptString(String input) {
        throw new UnsupportedOperationException();
    }

}
