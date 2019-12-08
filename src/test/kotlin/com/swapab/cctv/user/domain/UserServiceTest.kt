package com.swapab.cctv.user.domain

import com.swapab.cctv.user.domain.model.User
import com.swapab.cctv.user.exceptions.UserNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest {
    companion object {
        private const val USER_ID = "valid-user"
        private val creditCards = hashSetOf<String>()
        private val user = User(USER_ID, 10.0, creditCards)
    }

    @InjectMocks
    private lateinit var userService: UserService

    @Mock
    private lateinit var mockUserRepository: UserRepository

    @Test
    fun `create - should create a new user with userId and default balance`() {
        `when`(mockUserRepository.save(any(User::class.java))).thenReturn(User())

        val result = userService.create()

        verify(mockUserRepository).save(any(User::class.java))
        assertEquals(0.0, result.balance)
    }

    @Test
    fun `update - should find the user and add amount to balance`() {
        `when`(mockUserRepository.find(USER_ID)).thenReturn(Optional.of(user))

        val updatedUser = User(USER_ID, 15.0, creditCards)
        `when`(mockUserRepository.save(any(User::class.java))).thenReturn(updatedUser)

        val result = userService.update(USER_ID, 5.0)

        verify(mockUserRepository).find(USER_ID)
        verify(mockUserRepository).save(any(User::class.java))
        assertEquals(15.0, result.balance)
    }

    @Test
    fun `update - should throw UserNotFoundException if user does not exists`() {
        `when`(mockUserRepository.find(USER_ID)).thenReturn(Optional.empty())

        assertThrows<UserNotFoundException>() {
            userService.update(USER_ID, 5.0)
        }

        verifyNoMoreInteractions(mockUserRepository)
    }
}