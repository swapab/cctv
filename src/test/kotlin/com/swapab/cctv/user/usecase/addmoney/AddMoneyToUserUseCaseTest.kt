package com.swapab.cctv.user.usecase.addmoney

import com.swapab.cctv.BaseUseCaseTest
import com.swapab.cctv.user.domain.User
import com.swapab.cctv.user.usecase.getuser.GetUserUseCase
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.InjectMocks
import org.mockito.Mock

class AddMoneyToUserUseCaseTest : BaseUseCaseTest {
    companion object {
        private const val USER_ID = "valid-user"
        private val user = User(USER_ID, 0.0)
    }

    @InjectMocks
    private lateinit var addMoneyToUserUseCase: AddMoneyToUserUseCase

    @Mock
    private lateinit var getUserUseCase: GetUserUseCase

    @Mock
    private lateinit var updateBalanceInUser: UpdateUserWithBalance

    @Test
    fun `should find the user and add amount to balance`() {
        given(getUserUseCase.getUser(USER_ID)).willReturn(user)
        given(updateBalanceInUser.updateUserBalance(USER_ID, 5.0))
                .willReturn(User(USER_ID, 5.0))

        addMoneyToUserUseCase.addMoney(USER_ID, 5.0)

        verify(getUserUseCase).getUser(USER_ID)
        verify(updateBalanceInUser).updateUserBalance(USER_ID, 5.0)
    }
}