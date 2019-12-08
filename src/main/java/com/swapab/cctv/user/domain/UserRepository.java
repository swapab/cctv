package com.swapab.cctv.user.domain;

import com.swapab.cctv.user.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class UserRepository {
    private HashMap<String, User> users = new HashMap<>();

    public Optional<User> find(String userId) {
        if (users.containsKey(userId)) {
            return Optional.of(users.get(userId));
        }
        return Optional.empty();
    }

    public User save(User user) {
        return users.put(user.getUserId(), user);
    }
}
