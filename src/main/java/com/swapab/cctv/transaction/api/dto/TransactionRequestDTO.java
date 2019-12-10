package com.swapab.cctv.transaction.api.dto;

public class TransactionRequestDTO {
    private String cardId;
    private double amount;

    public TransactionRequestDTO(String cardId, double amount) {
        this.cardId = cardId;
        this.amount = amount;
    }

    public String getCardId() {
        return cardId;
    }

    public double getAmount() {
        return amount;
    }

    TransactionRequestDTO() {}
}
