package com.swapab.cctv.user.datastore;

import com.swapab.cctv.creditcard.usecase.DoesUserExists;
import com.swapab.cctv.user.domain.User;
import com.swapab.cctv.user.usecase.addmoney.GetUserByUserId;
import com.swapab.cctv.user.usecase.addmoney.UpdateUserWithBalance;
import com.swapab.cctv.user.usecase.register.CreateUserWithZeroBalance;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserDataStoreProvider implements
        CreateUserWithZeroBalance,
        GetUserByUserId,
        UpdateUserWithBalance,
        DoesUserExists {
    private static final double DEFAULT_BALANCE = 0.0;

    private Map<String, User> userStore;

    public UserDataStoreProvider(HashMap<String, User> userStore) {
        this.userStore = userStore;
    }

    @Override
    public User createUserWithZeroBalance() {
        User newUser = new User(UUID.randomUUID().toString(), DEFAULT_BALANCE);
        userStore.put(newUser.getUserId(), newUser);
        return newUser;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        if (userStore.containsKey(userId)) {
            return Optional.of(userStore.get(userId));
        }
        return Optional.empty();
    }

    @Override
    public User updateUserBalance(User user, double amount) {
        User updatedUser = new User(
                user.getUserId(),
                user.getBalance() + amount
        );
        return userStore.put(updatedUser.getUserId(), updatedUser);
    }

    @Override
    public boolean doesUserExists(String userId) {
        return userStore.containsKey(userId);
    }
}
