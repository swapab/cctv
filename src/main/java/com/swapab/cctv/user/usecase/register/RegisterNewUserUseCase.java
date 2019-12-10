package com.swapab.cctv.user.usecase.register;

import com.swapab.cctv.user.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class RegisterNewUserUseCase {
    private final CreateUserWithZeroBalance createUserWithZeroBalance;

    public RegisterNewUserUseCase(CreateUserWithZeroBalance createUserWithZeroBalance) {
        this.createUserWithZeroBalance = createUserWithZeroBalance;
    }

    public User register() {
        return createUserWithZeroBalance.createUserWithZeroBalance();
    }
}
