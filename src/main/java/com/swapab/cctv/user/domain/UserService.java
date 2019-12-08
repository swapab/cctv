package com.swapab.cctv.user.domain;

import com.swapab.cctv.user.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User create() {
        return new User();
    }

    public User update(String userId, Double amount) {
        return new User();
    }
}
