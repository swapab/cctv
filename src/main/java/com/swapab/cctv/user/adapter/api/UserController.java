package com.swapab.cctv.user.adapter.api;

import com.swapab.cctv.user.domain.UserService;
import com.swapab.cctv.user.domain.dto.UpdateUserRequestDTO;
import com.swapab.cctv.user.domain.dto.UserResponseDTO;
import com.swapab.cctv.user.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<UserResponseDTO> createUser() {
        User createdUser = userService.create();
        return new ResponseEntity<>(new UserResponseDTO(createdUser.getUserId(), createdUser.getBalance()), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{userId}")
    User updateUser(
            @RequestBody UpdateUserRequestDTO updateUserRequestDTO,
            @PathVariable String userId) {
        return userService.update(
                userId,
                updateUserRequestDTO.getAmount()
        );
    }
}
