package com.swapab.cctv.creditcard.usecase;

public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException() {
        super("User not found");
    }
}
