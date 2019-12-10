package com.swapab.cctv.user.api.dto;

public class UserResponseDTO {
    private String userId;
    private double balance;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserResponseDTO(String  userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
