package com.swapab.cctv.user.adapter.api

import com.swapab.cctv.toJsonString
import com.swapab.cctv.user.domain.dto.UpdateUserRequestDTO
import com.swapab.cctv.user.domain.model.User
import com.swapab.cctv.user.usecase.addmoney.UserNotFoundException
import com.swapab.cctv.user.usecase.addmoney.AddMoneyToUserUseCase
import com.swapab.cctv.user.usecase.register.RegisterNewUserUseCase
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    companion object {
        private const val userBaseUrl = "/users"
        private const val USER_ID = "user-id"
        private val user = User(USER_ID, 0.0)
        private val userId = user.userId
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var registerNewUserUseCase: RegisterNewUserUseCase

    @MockBean
    private lateinit var addMoneyToUserUseCase: AddMoneyToUserUseCase

    @Test
    fun `POST - createUser - should create a user with zero balance`() {
        given(registerNewUserUseCase.register()).willReturn(User(USER_ID, 0.0))

        mockMvc.perform(post(userBaseUrl))
                .andExpect(status().isCreated)
                .andExpect(content().json(
                        "{userId: $userId, balance: 0.0}"
                ))
    }

    @Test
    fun `PUT - updateUser - should update user balance`() {
        val user = User(USER_ID, 0.0)
        val amount = 10.0
        val updateUserRequestDTO = UpdateUserRequestDTO(amount)
        user.balance = amount
        given(addMoneyToUserUseCase.addMoney(user.userId, amount)).will {}

        mockMvc.perform(
                put("$userBaseUrl/$userId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserRequestDTO.toJsonString()))
                .andExpect(status().isOk)
    }

    @Test
    fun `PUT - updateUser - should return 404 user-not-found if user does not exists`() {
        val updateUserRequestDTO = UpdateUserRequestDTO(0.0)
        given(addMoneyToUserUseCase.addMoney(user.userId, 0.0)).willThrow(UserNotFoundException())

        mockMvc.perform(
                put("$userBaseUrl/${user.userId}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(updateUserRequestDTO.toJsonString()))
                .andExpect(status().isNotFound)
    }
}