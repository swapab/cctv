package com.swapab.cctv.user.usecase.addmoney

import com.swapab.cctv.BaseUseCaseTest
import com.swapab.cctv.user.domain.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import java.util.*

class AddMoneyToUserUseCaseTest : BaseUseCaseTest {
    companion object {
        private const val USER_ID = "valid-user"
        private val user = User(USER_ID, 0.0)
    }

    @InjectMocks
    private lateinit var addMoneyToUserUseCase: AddMoneyToUserUseCase

    @Mock
    private lateinit var getUserByUserId: GetUserByUserId

    @Mock
    private lateinit var updateBalanceInUser: UpdateUserWithBalance

    @Test
    fun `should find the user and add amount to balance`() {
        given(getUserByUserId.findByUserId(USER_ID)).willReturn(Optional.of(user))
        given(updateBalanceInUser.updateUserBalance(user, 5.0)).willReturn(User(USER_ID, 15.0))

        addMoneyToUserUseCase.addMoney(USER_ID, 5.0)

        verify(getUserByUserId).findByUserId(USER_ID)
        verify(updateBalanceInUser).updateUserBalance(user, 5.0)
    }

    @Test
    fun `should throw UserNotFoundException if user does not exists`() {
        given(getUserByUserId.findByUserId(USER_ID)).willReturn(Optional.empty())

        assertThrows<UserNotFoundException> {
            addMoneyToUserUseCase.addMoney(USER_ID, 5.0)
        }

        verifyNoInteractions(updateBalanceInUser)
    }
}