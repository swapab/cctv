package com.swapab.cctv.creditcard.adapter.api.dto;

public class CreditCardRequestDTO {
    private String creditCardNumber;

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public CreditCardRequestDTO() {}

    public CreditCardRequestDTO(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

}
