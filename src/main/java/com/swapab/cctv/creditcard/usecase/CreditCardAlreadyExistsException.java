package com.swapab.cctv.creditcard.usecase;

public class CreditCardAlreadyExistsException extends RuntimeException {
    public CreditCardAlreadyExistsException() {
        super("Credit Card already exists");
    }
}
