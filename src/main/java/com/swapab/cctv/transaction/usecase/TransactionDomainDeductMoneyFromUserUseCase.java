package com.swapab.cctv.transaction.usecase;

public interface TransactionDomainDeductMoneyFromUserUseCase {
    void addMoney(String userId, double amount);
}
