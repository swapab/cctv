package com.swapab.cctv.user.adapter.api.dto;

public class UserResponseDTO {
    private String userId;
    private Double balance;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UserResponseDTO(String  userId, Double balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
