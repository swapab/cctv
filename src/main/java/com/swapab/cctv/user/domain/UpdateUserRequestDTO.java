package com.swapab.cctv.user.domain;

public class UpdateUserRequestDTO {
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public UpdateUserRequestDTO() {}

    public UpdateUserRequestDTO(Double amount) {
        this.amount = amount;
    }
}
