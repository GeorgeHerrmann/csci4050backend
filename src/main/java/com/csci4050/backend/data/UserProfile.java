package com.csci4050.backend.data;

/**
 * An entity class that represents a user profile.
 * Implementator: George Herrmann
 */
public class UserProfile {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private UserStatus status;

    /**
     * Creates a new user profile object.
     * 
     * @param firstname First name of the user.
     * @param lastname Last name of the user.
     * @param email Email of the user.
     * @param password Password of the user.
     * @param address Address of the user.
     * @param paymentCard Payment card of the user.
     */
    public UserProfile(String id, String firstname, String lastname, String email, String password, UserStatus status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    /**
     * Creates a new user profile object.
     * 
     * @return First name of the user.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the first name of the user.
     * 
     * @param firstname First name of the user.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets the last name of the user.
     * 
     * @return Last name of the user.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the last name of the user.
     * 
     * @param lastname Last name of the user.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets the email of the user.
     * 
     * @return Email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * 
     * @param email Email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     * 
     * @return Password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password Password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the ID of the user.
     * 
     * @return ID of the user.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * 
     * @param id ID of the user.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the status of the user.
     * 
     * @return Status of the user.
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the user.
     * 
     * @param status Status of the user.
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
