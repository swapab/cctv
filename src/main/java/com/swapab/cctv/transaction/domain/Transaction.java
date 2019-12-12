package com.swapab.cctv.transaction.domain;

import java.util.UUID;

public class Transaction {
    private final String transactionId;
    private final String creditCardId;
    private final double amount;

    public String getTransactionId() {
        return transactionId;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction(String transactionId, String creditCardId, double amount) {
        this.transactionId = transactionId;
        this.creditCardId = creditCardId;
        this.amount = amount;
    }

    public Transaction(String creditCardId, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.creditCardId = creditCardId;
        this.amount = amount;
    }

}
