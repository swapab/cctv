package com.swapab.cctv.transaction.usecase

import com.swapab.cctv.BaseUseCaseTest
import com.swapab.cctv.creditcard.domain.CreditCard
import com.swapab.cctv.transaction.domain.Transaction
import com.swapab.cctv.user.domain.User
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.inOrder
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import java.util.*

class IssueCardTransactionUseCaseTest : BaseUseCaseTest {

    companion object {
        private const val USER_ID = "user-id"
        private const val NON_EXISTING_CARD_ID = "non-existing-card-id"
        private const val CREDIT_CARD_ID = "card-id"
        private val user = User(USER_ID, 9.8)
        private val creditCard = CreditCard(CREDIT_CARD_ID, USER_ID, "123")
    }

    @InjectMocks
    private lateinit var issueCardTransactionUseCase: IssueCardTransactionUseCase

    @Mock
    private lateinit var transactionDomainGetUserUseCase: TransactionDomainGetUserUseCase

    @Mock
    private lateinit var createNewTransactionForCard: CreateNewTransactionForCard

    @Mock
    private lateinit var transactionDomainDeductMoneyFromUserUseCase: TransactionDomainDeductMoneyFromUserUseCase

    @Mock
    private lateinit var transactionDomaingetCreditCardForUser: TransactionDomainGetCreditCardForUser

    @Test
    fun `issueTransaction - should throw InSufficientBalanceException if balance is less than transaction amount`() {
        given(transactionDomainGetUserUseCase.getUser(USER_ID)).willReturn(user)
        given(transactionDomaingetCreditCardForUser
                .getCreditCardByUserIdAndCreditCardId(USER_ID, CREDIT_CARD_ID)
        ).willReturn(Optional.of(creditCard))

        assertThrows<InSufficientBalanceException> {
            issueCardTransactionUseCase.issueTransaction(USER_ID, CREDIT_CARD_ID, 10.0)
        }
    }

    @Test
    fun `issueTransaction - should deduct user balance if enough balance available`() {
        given(transactionDomainGetUserUseCase.getUser(USER_ID)).willReturn(user)
        given(transactionDomaingetCreditCardForUser
                .getCreditCardByUserIdAndCreditCardId(USER_ID, CREDIT_CARD_ID)
        ).willReturn(Optional.of(creditCard))

        issueCardTransactionUseCase.issueTransaction(USER_ID, CREDIT_CARD_ID, 5.5)

        verify(transactionDomainDeductMoneyFromUserUseCase).addMoney(USER_ID, -5.5)
    }

    @Test
    fun `issueTransaction - should save transaction after deducting user balance`() {
        given(transactionDomainGetUserUseCase.getUser(USER_ID)).willReturn(user)
        given(transactionDomaingetCreditCardForUser
                .getCreditCardByUserIdAndCreditCardId(USER_ID, CREDIT_CARD_ID)
        ).willReturn(Optional.of(creditCard))

        issueCardTransactionUseCase.issueTransaction(USER_ID, CREDIT_CARD_ID, 5.5)

        val inOrder= inOrder(transactionDomainDeductMoneyFromUserUseCase, createNewTransactionForCard)
        inOrder.verify(transactionDomainDeductMoneyFromUserUseCase).addMoney(USER_ID, -5.5)
        inOrder.verify(createNewTransactionForCard).saveTransaction(any(Transaction::class.java))
    }

    @Test
    fun `issueTransaction - should throw CardNotFoundException card not found if card does not exists`() {
        given(transactionDomaingetCreditCardForUser
                .getCreditCardByUserIdAndCreditCardId(USER_ID, NON_EXISTING_CARD_ID)
        ).willReturn(Optional.empty())

        assertThrows<CardNotFoundException> {
            issueCardTransactionUseCase.issueTransaction(USER_ID, NON_EXISTING_CARD_ID, 1.0)
        }
    }
}
