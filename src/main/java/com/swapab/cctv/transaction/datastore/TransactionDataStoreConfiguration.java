package com.swapab.cctv.transaction.datastore;

import com.swapab.cctv.transaction.domain.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionDataStoreConfiguration {

    private static int MAX_TRANSACTION_LIMIT = 100;

    @Bean
    public TransactionDataStoreProvider getTransactionDataStoreProvider() {
        Transaction[] transactionStore = new Transaction[MAX_TRANSACTION_LIMIT];
        return new TransactionDataStoreProvider(transactionStore, MAX_TRANSACTION_LIMIT);
    }
}
