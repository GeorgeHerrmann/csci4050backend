package com.csci4050.api.service.sessionkey;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.auth0.jwt.exceptions.JWTCreationException;

public class RSA256SessionKeyFactory implements SessionKeyFactory {
    private static final int KEY_SIZE = 2048;

    public SessionKeyService createSessionKeyService() throws JWTCreationException {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(KEY_SIZE);
            KeyPair kp = kpg.generateKeyPair();

            RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();

            return new SessionKeyService(publicKey, privateKey);
        } catch (NoSuchAlgorithmException e) {
            throw new JWTCreationException("Error generating key pairs, invalid configuration", e);
        }
    }
}
