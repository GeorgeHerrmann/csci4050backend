package com.csci4050.api.service;

import java.util.regex.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Create a class which features a basic service which can validate data from
 * the frontend.
 * Things like, is the email string a valid email, are the address fields in the
 * Address object valid, etc.
 * There are a few methods that need to be implemented, but add more if we need
 * it.
 * 
 * Implementator: Kartikey
 */
public class DataValidationService {

    /**
     * Checks if the given string is a valid email.
     * 
     * @param email The string to check.
     * @return True if the string is a valid email, false otherwise.
     */
    public boolean isValidEmail(String email) {

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    String key = "sfknjowhfionqkfx";

    /**
     * Encrypts a string using a hashing algorithm.
     * You can use an outside library for this, it might be easier.
     * 
     * Thought of using bcrypt here but we are encrypting and not hashing so...
     * 
     * @param input The string to encrypt.
     * @return The encrypted string.
     */

    public String encryptString(String input) {

        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
            String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);

            return encryptedString;
        } catch (Exception e) {
            e.printStackTrace();
            return new String("Hello");
        }
    }

    /**
     * Decrypts a string using a hashing algorithm.
     * You can use an outside library for this, it might be easier.
     * 
     * @param input The string to decrypt.
     * @return The decrypted string.
     */
    public String decryptString(String input) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedBytes = Base64.getDecoder().decode(input);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

            return decryptedString;
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

}
