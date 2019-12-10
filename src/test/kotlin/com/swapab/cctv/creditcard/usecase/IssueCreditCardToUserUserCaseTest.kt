package com.swapab.cctv.creditcard.usecase

import com.swapab.cctv.BaseUseCaseTest
import com.swapab.cctv.creditcard.domain.CreditCard
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verifyNoInteractions
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify

class IssueCreditCardToUserUserCaseTest : BaseUseCaseTest {
    companion object {
        private const val USER_ID = "valid-user"
        private const val VALID_CREDIT_CARD = "0000000123456789"
        private const val INVALID_CREDIT_CARD = "00000123456789"
    }

    @InjectMocks
    private lateinit var issueCreditCardToUserUserCase: IssueCreditCardToUserUserCase

    @Mock
    private lateinit var doesUserExists: DoesUserExists

    @Mock
    private lateinit var saveCreditCard: SaveCreditCard

    @Mock
    private lateinit var validateCreditCardUseCase: ValidateCreditCardUseCase

    @Test
    fun `should assign a credit card to an existing user if credit is valid`() {
        given(doesUserExists.doesUserExists(USER_ID)).willReturn(true)
        given(validateCreditCardUseCase.isCreditCardNumberValid(VALID_CREDIT_CARD)).willReturn(true)
        given(saveCreditCard.saveCreditCardForUser(any(CreditCard::class.java))).willReturn(true)

        issueCreditCardToUserUserCase.assign(USER_ID, VALID_CREDIT_CARD)

        verify(doesUserExists).doesUserExists(USER_ID)
        verify(saveCreditCard).saveCreditCardForUser(any(CreditCard::class.java))
    }

    @Test
    fun `should throw CreditCardAlreadyExistsException if credit-card saveCreditCardForUser returned false`() {
        given(doesUserExists.doesUserExists(USER_ID)).willReturn(true)
        given(validateCreditCardUseCase.isCreditCardNumberValid(VALID_CREDIT_CARD)).willReturn(true)
        given(saveCreditCard.saveCreditCardForUser(any(CreditCard::class.java))).willReturn(false)

        assertThrows<CreditCardAlreadyExistsException> {
            issueCreditCardToUserUserCase.assign(USER_ID, VALID_CREDIT_CARD)
        }
    }

    @Test
    fun `should throw UserDoesNotExistsException if user does not exists`() {
        given(doesUserExists.doesUserExists(USER_ID)).willReturn(false)

        assertThrows<UserDoesNotExistsException> {
            issueCreditCardToUserUserCase.assign(USER_ID, VALID_CREDIT_CARD)
        }

        verify(doesUserExists).doesUserExists(USER_ID)
        verifyNoInteractions(saveCreditCard)
    }

    @Test
    fun `should throw InvalidCreditCardException if credit card is not valid`() {
        given(doesUserExists.doesUserExists(USER_ID)).willReturn(true)
        given(validateCreditCardUseCase.isCreditCardNumberValid(INVALID_CREDIT_CARD)).willReturn(false)

        assertThrows<InvalidCreditCardException> {
            issueCreditCardToUserUserCase.assign(USER_ID, INVALID_CREDIT_CARD)
        }

        verifyNoInteractions(saveCreditCard)
    }
}