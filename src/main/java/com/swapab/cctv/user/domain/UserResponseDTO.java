package com.swapab.cctv.user.domain;

import java.util.UUID;

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


    public UserResponseDTO(UUID userId, Double balance) {
        this.userId = userId.toString();
        this.balance = balance;
    }
}
