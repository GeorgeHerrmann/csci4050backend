package com.csci4050.api.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

/**
 * Create a class which features a basic service which can create and verify session keys.
 * We are going to use JWT tokens for session verification. I have already imported it.
 * A link to the documentation can be found here: https://github.com/auth0/java-jwt
 * 
 * Implementator: George
 */
public class SessionKeyService {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    /**
     * Creates a SessionKeyService, generating a new public and private key pair.
     * All session keys to be verified by this service must have been created by this service.
     * 
     * @throws JWTCreationException If the key pair could not be generated.
     */
    public SessionKeyService() throws JWTCreationException {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();

            publicKey = (RSAPublicKey) kp.getPublic();
            privateKey = (RSAPrivateKey) kp.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            throw new JWTCreationException("Error generating key pairs, invalid configuration", e);
        }
    }

    /**
     * Creates a new session key for this service.
     * 
     * @return A new session key.
     * @throws JWTCreationException If the session key could not be created.
     */
    public String createSessionKey() throws JWTCreationException {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            return  JWT.create().withIssuer("auth0").sign(algorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Verifies a session key for this service.
     * 
     * @param token The session key to verify.
     * @return True if the session key is valid, false otherwise.
     */
    public boolean verifySessionKey(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Verifies a session key for this service and returns the decoded JWT if
     * successful.
     * 
     * @param token The session key to verify.
     * @return The decoded JWT if the session key is valid, null otherwise.
     * @throws JWTVerificationException If the session key is invalid.
     */
    public DecodedJWT verifyAndGetJWT(String token) throws JWTVerificationException {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
                
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
