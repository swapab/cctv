package com.swapab.cctv.user.domain;

import java.util.UUID;

public class User {
    private UUID userId = UUID.randomUUID();
    private Double balance = 0.0;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
