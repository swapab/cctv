package com.swapab.cctv.transaction.usecase;

import com.swapab.cctv.user.domain.User;

public interface TransactionDomainGetUserUseCase {
    User getUser(String userId);
}
