package com.swapab.cctv.user.usecase.addmoney;

import org.springframework.stereotype.Service;

@Service
public class AddMoneyToUserUseCase {
    private final GetUserByUserId getUserByUserId;
    private final UpdateUserWithBalance updateUserWithBalance;

    public AddMoneyToUserUseCase(GetUserByUserId getUserByUserId, UpdateUserWithBalance updateUserWithBalance) {
        this.getUserByUserId = getUserByUserId;
        this.updateUserWithBalance = updateUserWithBalance;
    }

    public void addMoney(String userId, Double amount) {
        updateUserWithBalance.updateUserBalance(
                getUserByUserId
                        .findByUserId(userId)
                        .orElseThrow(UserNotFoundException::new),
                amount
        );
    }
}
