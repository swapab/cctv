package com.swapab.cctv.transaction.usecase;

public class InSufficientBalanceException extends RuntimeException {
    public InSufficientBalanceException() {
        super("InSufficient Balance");
    }
}
