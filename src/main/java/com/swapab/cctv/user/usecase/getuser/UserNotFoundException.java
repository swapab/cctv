package com.swapab.cctv.user.usecase.getuser;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}
