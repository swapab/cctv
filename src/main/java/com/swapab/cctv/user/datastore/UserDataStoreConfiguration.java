package com.swapab.cctv.user.datastore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class UserDataStoreConfiguration {

    @Bean
    public UserDataStoreProvider getUserDataStoreProvider() {
        return new UserDataStoreProvider(new ConcurrentHashMap<>());
    }
}
