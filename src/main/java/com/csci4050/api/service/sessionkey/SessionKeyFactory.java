package com.csci4050.api.service.sessionkey;

import com.auth0.jwt.exceptions.JWTCreationException;

public interface SessionKeyFactory {
    public SessionKeyService createSessionKeyService() throws JWTCreationException;
}
