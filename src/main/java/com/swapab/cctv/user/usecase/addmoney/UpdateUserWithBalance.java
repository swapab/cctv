package com.swapab.cctv.user.usecase.addmoney;

import com.swapab.cctv.user.domain.User;

public interface UpdateUserWithBalance {
    User updateUserBalance(String userId, double amount);
}
