package com.swapab.cctv.user.domain;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User create() {
        return new User();
    }

    public User update(Double amount) {
        return new User();
    }
}
