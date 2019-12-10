package com.swapab.cctv.user.usecase.addmoney;

import com.swapab.cctv.user.domain.model.User;

public interface UpdateUserWithBalance {
    User updateUserBalance(User user, Double amount);
}
