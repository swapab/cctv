package com.swapab.cctv.user.domain;

import com.swapab.cctv.user.domain.model.User;
import com.swapab.cctv.user.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create() {
        return userRepository.save(new User());
    }

    public User update(String userId, Double amount) {
        User existingUser = userRepository.find(userId).orElseThrow(UserNotFoundException::new);
        User updatedUser = new User(
                existingUser.getUserId(),
                existingUser.getBalance() + amount,
                existingUser.getCreditCards()
                );
        return userRepository.save(updatedUser);
    }
}
