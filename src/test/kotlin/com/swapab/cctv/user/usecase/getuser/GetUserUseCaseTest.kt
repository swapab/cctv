package com.swapab.cctv.user.usecase.getuser

import com.swapab.cctv.BaseUseCaseTest
import com.swapab.cctv.user.domain.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import java.util.*

class GetUserUseCaseTest : BaseUseCaseTest {

    companion object {
        private const val USER_ID = "valid-user"
        private val user = User(USER_ID, 0.0)
    }

    @InjectMocks
    private lateinit var getUserUseCase: GetUserUseCase

    @Mock
    private lateinit var getUserByUserId: GetUserByUserId

    @Test
    fun `should return user if exists`() {
        given(getUserByUserId.findByUserId(USER_ID)).willReturn(Optional.of(user))

        assertEquals(user, getUserUseCase.getUser(USER_ID))
    }

    @Test
    fun `should throw UserNotFoundException if user does not exists`() {
        given(getUserByUserId.findByUserId(USER_ID)).willReturn(Optional.empty())

        assertThrows<UserNotFoundException> {
            getUserUseCase.getUser(USER_ID)
        }
    }
}
