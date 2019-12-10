package com.swapab.cctv.user.usecase.addmoney;

import com.swapab.cctv.transaction.usecase.TransactionDomainDeductMoneyFromUserUseCase;
import com.swapab.cctv.user.usecase.getuser.GetUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class AddMoneyToUserUseCase implements TransactionDomainDeductMoneyFromUserUseCase {
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserWithBalance updateUserWithBalance;

    public AddMoneyToUserUseCase(GetUserUseCase getUserUseCase, UpdateUserWithBalance updateUserWithBalance) {
        this.getUserUseCase = getUserUseCase;
        this.updateUserWithBalance = updateUserWithBalance;
    }

    public void addMoney(String userId, double amount) {
        double currentBalance = getUserUseCase.getUser(userId).getBalance();
        double updatedBalance = currentBalance + amount;
        updateUserWithBalance.updateUserBalance(userId, updatedBalance);
    }
}
