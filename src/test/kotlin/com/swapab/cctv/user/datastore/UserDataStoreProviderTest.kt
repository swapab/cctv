package com.swapab.cctv.user.datastore

import com.swapab.cctv.user.domain.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserDataStoreProviderTest {

    private lateinit var userStore: HashMap<String, User>
    private lateinit var userDataStoreProvider: UserDataStoreProvider

    @BeforeEach
    fun setUp() {
        userStore = hashMapOf()
        userDataStoreProvider = UserDataStoreProvider(userStore)
    }

    @Test
    fun `createUserWithZeroBalance - should create a new User with default balance`() {
        val createdUser = userDataStoreProvider.createUserWithZeroBalance()
        assertEquals(0.0, userStore[createdUser.userId]?.balance)
    }

    @Test
    fun `findByUserId - should get the user for given userId`() {
        val existingUser = userDataStoreProvider.createUserWithZeroBalance()
        val actualUser = userDataStoreProvider.findByUserId(existingUser.userId)
        assertTrue(actualUser.isPresent)
        assertEquals(existingUser.userId, actualUser.get().userId)
    }

    @Test
    fun `updateUserBalance - should update the balance for the given user`() {
        val createdUser = userDataStoreProvider.createUserWithZeroBalance()
        userDataStoreProvider.updateUserBalance(createdUser, 15.50)
        assertEquals(15.50, userStore[createdUser.userId]?.balance)
    }

    @Test
    fun `doesUserExists - should return true if user exists`() {
        val createdUser = userDataStoreProvider.createUserWithZeroBalance()
        assertTrue(userDataStoreProvider.doesUserExists(createdUser.userId))
    }

    @Test
    fun `doesUserExists - should return false if user does not exists`() {
        assertFalse(userDataStoreProvider.doesUserExists("non-existing-user"))
    }
}
