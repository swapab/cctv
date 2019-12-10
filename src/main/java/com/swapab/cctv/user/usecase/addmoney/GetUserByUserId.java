package com.swapab.cctv.user.usecase.addmoney;

import com.swapab.cctv.user.domain.model.User;

import java.util.Optional;

public interface GetUserByUserId {
    Optional<User> findByUserId(String userId);
}
