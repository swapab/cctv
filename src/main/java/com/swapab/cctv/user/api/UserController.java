package com.swapab.cctv.user.api;

import com.swapab.cctv.user.api.dto.UpdateUserRequestDTO;
import com.swapab.cctv.user.api.dto.UserBalanceResponseDTO;
import com.swapab.cctv.user.api.dto.UserResponseDTO;
import com.swapab.cctv.user.api.exception.NotFoundException;
import com.swapab.cctv.user.domain.User;
import com.swapab.cctv.user.usecase.addmoney.AddMoneyToUserUseCase;
import com.swapab.cctv.user.usecase.getuser.GetUserUseCase;
import com.swapab.cctv.user.usecase.getuser.UserNotFoundException;
import com.swapab.cctv.user.usecase.register.RegisterNewUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RegisterNewUserUseCase registerNewUserUseCase;
    private final AddMoneyToUserUseCase addMoneyToUserUseCase;
    private final GetUserUseCase getUserUseCase;

    public UserController
            (RegisterNewUserUseCase registerNewUserUseCase,
             AddMoneyToUserUseCase addMoneyToUserUseCase,
             GetUserUseCase getUserUseCase) {
        this.registerNewUserUseCase = registerNewUserUseCase;
        this.addMoneyToUserUseCase = addMoneyToUserUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    @PostMapping
    ResponseEntity<UserResponseDTO> createUser() {
        User createdUser = registerNewUserUseCase.register();
        return new ResponseEntity<>(toDto(createdUser), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public void updateUser(
            @RequestBody UpdateUserRequestDTO updateUserRequestDTO,
            @PathVariable String userId) {
        try {
            addMoneyToUserUseCase.addMoney(
                    userId,
                    updateUserRequestDTO.getAmount()
            );
        } catch (UserNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @GetMapping(value = "/{userId}/balance")
    public UserBalanceResponseDTO getUserBalance(@PathVariable String userId) {
        try {
            return new UserBalanceResponseDTO(getUserUseCase.getUser(userId).getBalance());
        } catch (UserNotFoundException e) {
            throw new NotFoundException();
        }
    }

    private UserResponseDTO toDto(User user) {
        return new UserResponseDTO(user.getUserId(), user.getBalance());
    }
}
