package com.swapab.cctv.user.domain.model;

public class User {
    private String userId;
    private Double balance;

    public String getUserId() {
        return userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User() {}

    public User(String userId, Double balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
