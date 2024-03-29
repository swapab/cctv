package com.swapab.cctv.transaction.datastore;

import com.swapab.cctv.transaction.domain.Transaction;
import com.swapab.cctv.transaction.usecase.CreateNewTransactionForCard;
import com.swapab.cctv.transaction.usecase.GetAllTransactions;

import java.util.concurrent.atomic.AtomicInteger;

public class TransactionDataStoreProvider implements
        CreateNewTransactionForCard,
        GetAllTransactions {
    private Transaction[] transactionStore;
    private int transactionPoolSize;
    private AtomicInteger counter = new AtomicInteger();

    public TransactionDataStoreProvider(Transaction[] transactionStore, int transactionPoolSize) {
        this.transactionStore = transactionStore;
        this.transactionPoolSize = transactionPoolSize;
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        this.transactionStore[counter.intValue()] = transaction;

        if(counter.incrementAndGet() == transactionPoolSize) {
            counter.set(0);
        }
    }

    @Override
    public Transaction[] getAll() {
        return this.transactionStore;
    }
}
