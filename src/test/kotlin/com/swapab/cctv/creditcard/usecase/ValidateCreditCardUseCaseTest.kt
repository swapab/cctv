package com.swapab.cctv.creditcard.usecase

import com.swapab.cctv.BaseUseCaseTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks

class ValidateCreditCardUseCaseTest : BaseUseCaseTest {
    @InjectMocks
    private lateinit var validateCreditCardUseCase: ValidateCreditCardUseCase

    @Test
    fun `should return true for a valid credit card`() {
        val validCreditCardNumber = "379354508162306"
        assertThat(
                validateCreditCardUseCase.isCreditCardNumberValid(validCreditCardNumber)
        ).isTrue()
    }

    @Test
    fun `should return false for an invalid credit card`() {
        val inValidCreditCardNumber = "4388576018402626"
        assertThat(
                validateCreditCardUseCase.isCreditCardNumberValid(inValidCreditCardNumber)
        ).isFalse()
    }
}
