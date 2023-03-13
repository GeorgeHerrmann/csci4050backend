package com.csci4050.backend.data;

/**
 * The PaymentCard class represents a user's payment card.
 * Implementator: George Herrmann
 */
public class PaymentCard {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;

    /**
     * Creates a PaymentCard object that will hold a user's payment card.
     * 
     * @param cardNumber The card number.
     * @param cardHolderName The card holder's name.
     * @param expirationDate The expiration date.
     * @param cvv The CVV.
     */
    public PaymentCard(String cardNumber, String cardHolderName, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    /**
     * Returns the card number.
     * 
     * @return The card number.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Returns the card number as an integer.
     * 
     * @return The card number as an integer.
     */
    public int getCardNumberInt() {
        return Integer.parseInt(cardNumber);
    }

    /**
     * Returns the card holder's name.
     * 
     * @return The card holder's name.
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Returns the expiration date.
     * 
     * @return The expiration date.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Returns the CVV.
     * 
     * @return The CVV.
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Returns the CVV as an integer.
     * 
     * @return The CVV as an integer.
     */
    public int getCvvInt() {
        return Integer.parseInt(cvv);
    }
}
