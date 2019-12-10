package com.swapab.cctv.user.datastore;

import com.swapab.cctv.user.domain.model.User;
import com.swapab.cctv.user.usecase.addmoney.GetUserByUserId;
import com.swapab.cctv.user.usecase.addmoney.UpdateUserWithBalance;
import com.swapab.cctv.user.usecase.register.CreateUserWithZeroBalance;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDataStoreProvider implements CreateUserWithZeroBalance, GetUserByUserId, UpdateUserWithBalance {
    private static final Double DEFAULT_BALANCE = 0.0;

    private HashMap<String, User> users = new HashMap<>();

    @Override
    public User createUserWithZeroBalance() {
        User newUser = new User(UUID.randomUUID().toString(), DEFAULT_BALANCE);
        users.put(newUser.getUserId(), newUser);
        return newUser;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        if (users.containsKey(userId)) {
            return Optional.of(users.get(userId));
        }
        return Optional.empty();
    }

    @Override
    public User updateUserBalance(User user, Double amount) {
        User updatedUser = new User(
                user.getUserId(),
                user.getBalance() + amount
        );
        return users.put(updatedUser.getUserId(), updatedUser);
    }
}
