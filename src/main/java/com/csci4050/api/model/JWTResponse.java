package com.csci4050.api.model;

public class JWTResponse {
    private String key;
    private String success;
    private User user;

    public JWTResponse(String key, String success, User user) {
        this.key = key;
        this.success = success;
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
