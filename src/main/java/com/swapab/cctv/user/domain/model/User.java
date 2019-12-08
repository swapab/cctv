package com.swapab.cctv.user.domain.model;

import java.util.UUID;

public class User {
    private String userId = UUID.randomUUID().toString();
    private Double balance = 0.0;

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
}
