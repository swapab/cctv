package com.swapab.cctv.user.adapter.api;

import com.swapab.cctv.user.adapter.api.dto.UpdateUserRequestDTO;
import com.swapab.cctv.user.adapter.api.dto.UserResponseDTO;
import com.swapab.cctv.user.adapter.api.exception.NotFoundException;
import com.swapab.cctv.user.domain.User;
import com.swapab.cctv.user.usecase.addmoney.AddMoneyToUserUseCase;
import com.swapab.cctv.user.usecase.addmoney.UserNotFoundException;
import com.swapab.cctv.user.usecase.register.RegisterNewUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RegisterNewUserUseCase registerNewUserUseCase;
    private final AddMoneyToUserUseCase addMoneyToUserUseCase;

    public UserController(RegisterNewUserUseCase registerNewUserUseCase, AddMoneyToUserUseCase addMoneyToUserUseCase) {
        this.registerNewUserUseCase = registerNewUserUseCase;
        this.addMoneyToUserUseCase = addMoneyToUserUseCase;
    }

    @PostMapping
    ResponseEntity<UserResponseDTO> createUser() {
        User createdUser = registerNewUserUseCase.register();
        return new ResponseEntity<>(toDto(createdUser), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    void updateUser(
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

    private UserResponseDTO toDto(User user) {
        return new UserResponseDTO(user.getUserId(), user.getBalance());
    }
}
