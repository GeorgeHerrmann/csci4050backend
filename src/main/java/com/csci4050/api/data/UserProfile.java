package com.csci4050.api.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfile {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Address address;
    private UserStatus status;
   
}
