package com.swapab.cctv.creditcard.domain;

public class CreditCard {
    private final String creditCardId;
    private final String userId;
    private final String creditCardNumber;

    public CreditCard(String creditCardId, String userId, String creditCardNumber) {
        this.creditCardId = creditCardId;
        this.userId = userId;
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }
}
