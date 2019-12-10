package com.swapab.cctv.creditcard.usecase;

public class InvalidCreditCardException extends RuntimeException {
    public InvalidCreditCardException() {
        super("Invalid Credit Card");
    }
}
