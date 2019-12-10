package com.swapab.cctv.transaction.datastore

import com.swapab.cctv.transaction.domain.Transaction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class TransactionDataStoreProviderTest {
    companion object {
        private const val MAX_TRANSACTION_POOL_SIZE = 1
        private const val creditCardOne = "card-id-1"
        private const val creditCardTwo = "card-id-2"
    }

    private lateinit var transactionDataStoreProvider: TransactionDataStoreProvider

    private lateinit var transactionStore: Array<Transaction?>

    @BeforeEach
    fun setUp() {
        transactionStore = arrayOfNulls(1) // emptyArray() // arrayOf()
        transactionDataStoreProvider = TransactionDataStoreProvider(transactionStore, MAX_TRANSACTION_POOL_SIZE)
    }

    @Test
    fun `saveTransaction - should save the transaction to data-store`() {
        val transaction = Transaction(creditCardOne, 5.50)
        transactionDataStoreProvider.saveTransaction(transaction)
        assertThat(transactionStore).contains(transaction)
    }

    @Test
    fun `saveTransaction - should reset counter if max transaction pool size reached`() {
        val transactionOne = Transaction(creditCardOne, 5.50)
        val transactionTwo = Transaction(creditCardTwo, 9.15)
        transactionDataStoreProvider.saveTransaction(transactionOne)
        assertDoesNotThrow {
            transactionDataStoreProvider.saveTransaction(transactionTwo)
        }
    }
}