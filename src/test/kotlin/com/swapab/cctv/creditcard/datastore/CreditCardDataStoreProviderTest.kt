package com.swapab.cctv.creditcard.datastore

import com.swapab.cctv.creditcard.domain.CreditCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class CreditCardDataStoreProviderTest {

    companion object {
        private const val USER_ID = "user-id-1"
        private const val CREDIT_CARD_ID_ONE = "card-id-1"
        private const val CREDIT_CARD_ID_TWO = "card-id-2"
        private val newCreditCardOne = CreditCard(CREDIT_CARD_ID_ONE, USER_ID, "cc-no-1")
        private val newCreditCardTwo = CreditCard(CREDIT_CARD_ID_TWO, USER_ID, "cc-no-2")
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

    @Test
    fun `getCreditCardByUserIdAndCreditCardId - should return credit card for user`() {
        creditCardDataStoreProvider.saveCreditCardForUser(newCreditCardOne)

        assertThat(
                creditCardDataStoreProvider
                        .getCreditCardByUserIdAndCreditCardId(USER_ID, CREDIT_CARD_ID_ONE)
        ).isEqualTo(Optional.of(newCreditCardOne))
    }

    @Test
    fun `getCreditCardByUserIdAndCreditCardId - should return empty if user has no cards`() {
        assertThat(
                creditCardDataStoreProvider
                        .getCreditCardByUserIdAndCreditCardId(USER_ID, CREDIT_CARD_ID_ONE)
        ).isEmpty
    }

    @Test
    fun `getCreditCardByUserIdAndCreditCardId - should return empty if credit card not found`() {
        creditCardDataStoreProvider.saveCreditCardForUser(newCreditCardOne)

        assertThat(
                creditCardDataStoreProvider
                        .getCreditCardByUserIdAndCreditCardId(USER_ID, CREDIT_CARD_ID_TWO)
        ).isEmpty
    }
}