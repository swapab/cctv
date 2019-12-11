package com.swapab.cctv.transaction.usecase;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException() {
        super("Card Not Found Exception");
    }
}
