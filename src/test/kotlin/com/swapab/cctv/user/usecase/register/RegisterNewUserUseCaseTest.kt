package com.swapab.cctv.user.usecase.register

import com.swapab.cctv.BaseUseCaseTest
import com.swapab.cctv.user.domain.User
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify

class RegisterNewUserUseCaseTest : BaseUseCaseTest {
    companion object {
        private const val USER_ID = "valid-user"
        private val user = User(USER_ID, 0.0)
    }

    @Mock
    private lateinit var createUserWithZeroBalance: CreateUserWithZeroBalance

    @Test
    fun `should register and return the new user with zero balance`() {
        given(createUserWithZeroBalance.createUserWithZeroBalance()).willReturn(user)

        createUserWithZeroBalance.createUserWithZeroBalance()

        verify(createUserWithZeroBalance).createUserWithZeroBalance()
    }
}