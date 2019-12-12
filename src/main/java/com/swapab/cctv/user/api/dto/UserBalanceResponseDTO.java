package com.swapab.cctv.user.api.dto;

public class UserBalanceResponseDTO {
    private final double balance;

    public double getBalance() {
        return balance;
    }

    public UserBalanceResponseDTO(double balance) {
        this.balance = balance;
    }
}
