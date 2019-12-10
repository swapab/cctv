package com.swapab.cctv.user.usecase.getuser;

import com.swapab.cctv.user.domain.User;

import java.util.Optional;

public interface GetUserByUserId {
    Optional<User> findByUserId(String userId);
}
