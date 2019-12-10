package com.swapab.cctv.user.domain;

public class User {
    private final String userId;
    private final double balance;

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public User(String userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
