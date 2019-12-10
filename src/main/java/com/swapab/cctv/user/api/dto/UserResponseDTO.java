package com.swapab.cctv.user.api.dto;

public class UserResponseDTO {
    private final String userId;
    private final double balance;

    public String getUserId() {
        return userId;
    }

    public double getBalance() { return balance; }

    public UserResponseDTO(String  userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
