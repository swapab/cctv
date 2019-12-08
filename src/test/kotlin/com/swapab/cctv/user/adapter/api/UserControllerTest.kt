package com.swapab.cctv.user.adapter.api

import com.swapab.cctv.toJsonString
import com.swapab.cctv.user.domain.dto.UpdateUserRequestDTO
import com.swapab.cctv.user.domain.model.User
import com.swapab.cctv.user.domain.UserService
import com.swapab.cctv.user.exceptions.UserNotFoundException
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    companion object {
        private const val userBaseUrl = "/users"
        private val user = User()
        private val userId = user.userId
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun `POST - createUser - should create a user with zero balance`() {
        given(userService.create()).willReturn(user)

        val result = mockMvc.perform(post(userBaseUrl))

        result.andExpect(status().isCreated)
                .andExpect(
                        content().json(
                                "{userId: $userId, balance: 0.0}"
                        )
                )
    }

    @Test
    fun `POST - updateUser - should update user balance`() {
        val amount = 10.0
        val updateUserRequestDTO = UpdateUserRequestDTO(amount)
        user.balance = amount
        given(userService.update(user.userId, amount)).willReturn(user)

        val result = mockMvc.perform(
                post("$userBaseUrl/$userId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserRequestDTO.toJsonString())
        )

        result.andExpect(status().isOk)
                .andExpect(
                        content().json(
                                "{userId: $userId, balance: $amount}"
                        )
                )
    }

    @Test
    fun `POST - updateUser - should return 404 user-not-found if user does not exists`() {
        val updateUserRequestDTO = UpdateUserRequestDTO(0.0)
        given(userService.update(user.userId, 0.0)).willThrow(UserNotFoundException())

        val result = mockMvc.perform(
                post("$userBaseUrl/${user.userId}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(updateUserRequestDTO.toJsonString())
        )

        result.andExpect(status().isNotFound)
    }
}