package com.swapab.cctv.user.adapter.api;

import com.swapab.cctv.user.domain.UpdateUserRequestDTO;
import com.swapab.cctv.user.domain.User;
import com.swapab.cctv.user.domain.UserResponseDTO;
import com.swapab.cctv.user.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity<UserResponseDTO> createUser() {
        User createdUser = userService.create();
        return new ResponseEntity<>(new UserResponseDTO(createdUser.getUserId(), createdUser.getBalance()), HttpStatus.CREATED);
    }

    @PostMapping(
            value = "/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    User updateUser(
            @RequestBody UpdateUserRequestDTO updateUserRequestDTO,
            @PathVariable String userId) {
        User updatedUser = userService.update(updateUserRequestDTO.getAmount());
        return updatedUser;
    }
}
