package com.swapab.cctv.user.usecase.getuser;

import com.swapab.cctv.transaction.usecase.TransactionDomainGetUserUseCase;
import com.swapab.cctv.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class GetUserUseCase implements TransactionDomainGetUserUseCase {
    private final GetUserByUserId getUserByUserId;

    public GetUserUseCase(GetUserByUserId getUserByUserId) {
        this.getUserByUserId = getUserByUserId;
    }

    public User getUser(String userId) {
        return getUserByUserId
                .findByUserId(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
