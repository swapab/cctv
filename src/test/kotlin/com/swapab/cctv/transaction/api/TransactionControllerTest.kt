package com.swapab.cctv.transaction.api

import com.swapab.cctv.BaseControllerTest
import com.swapab.cctv.toJsonString
import com.swapab.cctv.transaction.api.dto.TransactionRequestDTO
import com.swapab.cctv.transaction.domain.Transaction
import com.swapab.cctv.transaction.usecase.CardNotFoundException
import com.swapab.cctv.transaction.usecase.GetAllTransactionsUseCase
import com.swapab.cctv.transaction.usecase.InSufficientBalanceException
import com.swapab.cctv.transaction.usecase.IssueCardTransactionUseCase
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TransactionControllerTest : BaseControllerTest<TransactionController>() {

    companion object {
        private const val USER_ID = "user-id"
        private const val BASE_URL = "/users/$USER_ID/transactions"
        private const val CREDIT_CARD_ID = "card-id-1"
    }

    @MockBean
    private lateinit var issueCardTransactionUseCase: IssueCardTransactionUseCase

    @MockBean
    private lateinit var getAllTransactionUseCase: GetAllTransactionsUseCase

    override lateinit var subject: TransactionController

    @Test
    fun `issueTransaction - should return 200 OK if transaction recorded successfully`() {
        given(issueCardTransactionUseCase.issueTransaction(USER_ID, CREDIT_CARD_ID, 99.99)).will {  }

        mockMvc.perform(post(BASE_URL)
                .content(TransactionRequestDTO(CREDIT_CARD_ID, 99.99).toJsonString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
    }

    @Test
    fun `issueTransaction - should return 403 Forbidden for InSufficientBalanceException`() {
        given(
            issueCardTransactionUseCase.issueTransaction(USER_ID, CREDIT_CARD_ID, 99.99)
        ).willThrow(InSufficientBalanceException())

        mockMvc.perform(post(BASE_URL)
                .content(TransactionRequestDTO(CREDIT_CARD_ID, 99.99).toJsonString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden)
    }

    @Test
    fun `issueTransaction - should return 404 NotFound`() {
        given(
            issueCardTransactionUseCase.issueTransaction(USER_ID, CREDIT_CARD_ID, 99.99)
        ).willThrow(CardNotFoundException())

        mockMvc.perform(post(BASE_URL)
                .content(TransactionRequestDTO(CREDIT_CARD_ID, 99.99).toJsonString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound)
    }

    @Test
    fun `getAllTransactions - should return all transactions`() {
        given(getAllTransactionUseCase.allTransactions).willReturn(
                listOf(Transaction( "cc-id-1", 1.5))
        )

        mockMvc.perform(
                get("/transactions").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
    }
}
