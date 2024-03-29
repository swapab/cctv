package com.swapab.cctv.user.api

import com.swapab.cctv.BaseControllerTest
import com.swapab.cctv.toJsonString
import com.swapab.cctv.user.api.dto.UpdateUserRequestDTO
import com.swapab.cctv.user.api.dto.UserBalanceResponseDTO
import com.swapab.cctv.user.domain.User
import com.swapab.cctv.user.usecase.addmoney.AddMoneyToUserUseCase
import com.swapab.cctv.user.usecase.getuser.GetUserUseCase
import com.swapab.cctv.user.usecase.getuser.UserNotFoundException
import com.swapab.cctv.user.usecase.register.RegisterNewUserUseCase
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class UserControllerTest : BaseControllerTest<UserController>() {

    companion object {
        private const val userBaseUrl = "/users"
        private const val USER_ID = "user-id"
    }

    override lateinit var subject: UserController

    @MockBean
    private lateinit var registerNewUserUseCase: RegisterNewUserUseCase

    @MockBean
    private lateinit var addMoneyToUserUseCase: AddMoneyToUserUseCase

    @MockBean
    private lateinit var getUserUseCase: GetUserUseCase

    @Test
    fun `POST - createUser - should create a user with zero balance`() {
        given(registerNewUserUseCase.register()).willReturn(User(USER_ID, 0.0))

        mockMvc.perform(post(userBaseUrl))
                .andExpect(status().isCreated)
                .andExpect(content().json(
                        "{userId: $USER_ID, balance: 0.0}"
                ))
    }

    @Test
    fun `PUT - updateUser - should update user balance`() {
        val amount = 10.0
        val updateUserRequestDTO = UpdateUserRequestDTO(amount)
        given(addMoneyToUserUseCase.addMoney(USER_ID, amount)).will {}

        mockMvc.perform(
                put("$userBaseUrl/$USER_ID")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserRequestDTO.toJsonString()))
                .andExpect(status().isOk)
    }

    @Test
    fun `PUT - updateUser - should return 404 user-not-found if user does not exists`() {
        val updateUserRequestDTO = UpdateUserRequestDTO(0.0)
        given(addMoneyToUserUseCase.addMoney(USER_ID, 0.0)).willThrow(UserNotFoundException())

        mockMvc.perform(
                put("$userBaseUrl/$USER_ID")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserRequestDTO.toJsonString()))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `GET - getUserBalance - should return 404 NotFound is user does not exists`() {
        given(getUserUseCase.getUser(USER_ID)).willThrow(UserNotFoundException())

        mockMvc.perform(
                get("$userBaseUrl/$USER_ID/balance"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `GET - getUserBalance - should return 200 OK with user balance`() {
        val balance = 10.0
        val userBalanceResponseDTO = UserBalanceResponseDTO(balance)
        given(getUserUseCase.getUser(USER_ID)).willReturn(User(USER_ID, balance))

        mockMvc.perform(
                get("$userBaseUrl/$USER_ID/balance")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().json(userBalanceResponseDTO.toJsonString()))
    }
}
