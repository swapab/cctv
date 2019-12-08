package com.swapab.cctv.user.domain.model;

import java.util.HashSet;
import java.util.UUID;

public class User {
    private String userId = UUID.randomUUID().toString();
    private Double balance = 0.0;
    private HashSet<String> creditCards;

    public HashSet<String> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(HashSet<String> creditCards) {
        this.creditCards = creditCards;
    }

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

    public User() {}

    public User(String userId, Double balance, HashSet<String> creditCards) {
        this.userId = userId;
        this.balance = balance;
        this.creditCards = creditCards;
    }
}
