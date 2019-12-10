package com.swapab.cctv.user.api.dto;

public class UpdateUserRequestDTO {
    private double amount;

    public double getAmount() {
        return amount;
    }

    public UpdateUserRequestDTO(double amount) {
        this.amount = amount;
    }

    public UpdateUserRequestDTO() {}
}
