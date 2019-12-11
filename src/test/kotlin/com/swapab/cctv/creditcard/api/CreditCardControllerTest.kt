package com.swapab.cctv.creditcard.api

import com.swapab.cctv.BaseControllerTest
import com.swapab.cctv.creditcard.api.dto.CreditCardRequestDTO
import com.swapab.cctv.creditcard.domain.CreditCard
import com.swapab.cctv.creditcard.usecase.CreditCardAlreadyExistsException
import com.swapab.cctv.creditcard.usecase.InvalidCreditCardException
import com.swapab.cctv.creditcard.usecase.IssueCreditCardToUserUseCase
import com.swapab.cctv.creditcard.usecase.UserDoesNotExistsException
import com.swapab.cctv.toJsonString
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CreditCardControllerTest : BaseControllerTest<CreditCardController>() {
    companion object {
        private const val USER_ID = "user-id"
        private const val BASE_URL = "/users/$USER_ID/creditCards"
        private const val CREDIT_CARD_NUMBER = "123"
        private const val CREDIT_CARD_ID = "777"
    }

    override lateinit var subject: CreditCardController

    @MockBean
    private lateinit var issueCreditCardToUserUseCase: IssueCreditCardToUserUseCase

    @Test
    fun `POST - createCreditCard - should issue a credit-card for the user`() {
        val creditCard = CreditCard(CREDIT_CARD_ID, USER_ID, CREDIT_CARD_NUMBER)
        given(issueCreditCardToUserUseCase.assign(USER_ID, CREDIT_CARD_NUMBER)).willReturn(creditCard)

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CreditCardRequestDTO(CREDIT_CARD_NUMBER).toJsonString()))
                .andExpect(status().isCreated)
                .andExpect(content().json(creditCard.toJsonString()))
    }

    @Test
    fun `POST - createCreditCard - should return 404 not-found if user does not exists`() {
        given(issueCreditCardToUserUseCase.assign(USER_ID, CREDIT_CARD_NUMBER)).willThrow(UserDoesNotExistsException())

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CreditCardRequestDTO(CREDIT_CARD_NUMBER).toJsonString()))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `POST - createCreditCard - should return 400 bad-request if credit-card-invalid`() {
        given(issueCreditCardToUserUseCase.assign(USER_ID, CREDIT_CARD_NUMBER)).willThrow(InvalidCreditCardException())

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CreditCardRequestDTO(CREDIT_CARD_NUMBER).toJsonString()))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun `POST - createCreditCard - should return 400 bad-request if credit-card already exists`() {
        given(issueCreditCardToUserUseCase.assign(USER_ID, CREDIT_CARD_NUMBER)).willThrow(CreditCardAlreadyExistsException())

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(CreditCardRequestDTO(CREDIT_CARD_NUMBER).toJsonString()))
                .andExpect(status().isBadRequest)
    }
}