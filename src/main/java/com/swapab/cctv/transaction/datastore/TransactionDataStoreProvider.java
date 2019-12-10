package com.swapab.cctv.transaction.datastore;

import com.swapab.cctv.transaction.domain.Transaction;
import com.swapab.cctv.transaction.usecase.CreateNewTransactionForCard;

public class TransactionDataStoreProvider implements CreateNewTransactionForCard {
    private Transaction[] transactionStore;
    private int transactionPoolSize;
    private int counter = 0;

    public TransactionDataStoreProvider(Transaction[] transactionStore, int transactionPoolSize) {
        this.transactionStore = transactionStore;
        this.transactionPoolSize = transactionPoolSize;
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        this.transactionStore[counter] = transaction;
        counter++;
        if(counter == transactionPoolSize) {
            counter = 0;
        }
    }
}
