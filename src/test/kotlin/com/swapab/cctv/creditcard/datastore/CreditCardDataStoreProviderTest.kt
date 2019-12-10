package com.swapab.cctv.creditcard.datastore

import com.swapab.cctv.creditcard.domain.CreditCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreditCardDataStoreProviderTest {

    companion object {
        private const val USER_ID = "user-id-1"
        private val newCreditCardOne = CreditCard("card-id-1", USER_ID, "cc-no-1")
        private val newCreditCardTwo = CreditCard("card-id-2", USER_ID, "cc-no-2")
    }

    private lateinit var creditCardDataStoreProvider: CreditCardDataStoreProvider

    private lateinit var userCreditCards: HashMap<String, Set<CreditCard>>

    @BeforeEach
    fun setUp() {
        userCreditCards = hashMapOf()
        creditCardDataStoreProvider = CreditCardDataStoreProvider(userCreditCards)
        assertThat(userCreditCards[USER_ID]).isNullOrEmpty()
    }

    @Test
    fun `saveCreditCardForUser - should save the credit card to dataStore`() {
        creditCardDataStoreProvider.saveCreditCardForUser(newCreditCardOne)

        assertThat(userCreditCards[USER_ID]).contains(newCreditCardOne)
    }

    @Test
    fun `saveCreditCardForUser - can add multiple credit-cards to same user`() {
        creditCardDataStoreProvider.saveCreditCardForUser(newCreditCardOne)
        creditCardDataStoreProvider.saveCreditCardForUser(newCreditCardTwo)

        assertThat(userCreditCards[USER_ID]).contains(newCreditCardOne)
        assertThat(userCreditCards[USER_ID]).contains(newCreditCardTwo)
    }
}