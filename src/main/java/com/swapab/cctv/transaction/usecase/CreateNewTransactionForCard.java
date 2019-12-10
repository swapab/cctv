package com.swapab.cctv.transaction.usecase;

import com.swapab.cctv.transaction.domain.Transaction;

public interface CreateNewTransactionForCard {
    void saveTransaction(Transaction transaction);
}
