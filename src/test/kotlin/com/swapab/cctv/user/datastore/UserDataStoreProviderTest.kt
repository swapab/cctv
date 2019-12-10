package com.swapab.cctv.user.datastore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserDataStoreProviderTest {

    private lateinit var userDataStoreProvider: UserDataStoreProvider

    @BeforeEach
    fun setUp() {
        userDataStoreProvider = UserDataStoreProvider()
    }

    @Test
    fun `createUserWithZeroBalance - should create a new User with default balance`() {
        val createdUser = userDataStoreProvider.createUserWithZeroBalance()
        assertEquals(0.0, createdUser.balance)
    }

    @Test
    fun `findByUserId - should get the user for given userId`() {
        val existingUser = userDataStoreProvider.createUserWithZeroBalance()
        val actualUser = userDataStoreProvider.findByUserId(existingUser.userId)
        assertTrue(actualUser.isPresent)
        assertEquals(existingUser.userId, actualUser.get().userId)
    }
}
