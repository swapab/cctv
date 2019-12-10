package com.swapab.cctv.creditcard.datastore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class CreditCardDataStoreConfiguration {

    @Bean
    public CreditCardDataStoreProvider getCreditCardDataStoreProvider() {
        return new CreditCardDataStoreProvider(new HashMap<>());
    }
}
