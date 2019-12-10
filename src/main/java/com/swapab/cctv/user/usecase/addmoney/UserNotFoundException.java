package com.swapab.cctv.user.usecase.addmoney;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}
