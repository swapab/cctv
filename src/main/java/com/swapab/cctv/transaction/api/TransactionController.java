package com.swapab.cctv.transaction.api;

import com.swapab.cctv.creditcard.api.exceptions.NotFoundError;
import com.swapab.cctv.transaction.api.dto.TransactionRequestDTO;
import com.swapab.cctv.transaction.api.exceptions.ForbiddenError;
import com.swapab.cctv.transaction.domain.Transaction;
import com.swapab.cctv.transaction.usecase.CardNotFoundException;
import com.swapab.cctv.transaction.usecase.GetAllTransactionsUseCase;
import com.swapab.cctv.transaction.usecase.InSufficientBalanceException;
import com.swapab.cctv.transaction.usecase.IssueCardTransactionUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private final IssueCardTransactionUseCase issueCardTransactionUseCase;
    private final GetAllTransactionsUseCase getAllTransactionsUseCase;

    public TransactionController(
            IssueCardTransactionUseCase issueCardTransactionUseCase,
             GetAllTransactionsUseCase getAllTransactionsUseCase) {
        this.issueCardTransactionUseCase = issueCardTransactionUseCase;
        this.getAllTransactionsUseCase = getAllTransactionsUseCase;
    }

    @PostMapping("/users/{userId}/transactions")
    @ResponseStatus(HttpStatus.OK)
    public void issueTransaction(
            @RequestBody TransactionRequestDTO transactionRequestDTO,
            @PathVariable String userId
    ) {
        try {
            issueCardTransactionUseCase.issueTransaction(
                    userId,
                    transactionRequestDTO.getCardId(),
                    transactionRequestDTO.getAmount()
            );
        } catch (InSufficientBalanceException e) {
            throw new ForbiddenError(e.getMessage());
        } catch (CardNotFoundException e) {
            throw new NotFoundError(e.getMessage());
        }
    }

    @GetMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaction> getAllTransactions() {
        return getAllTransactionsUseCase.getAllTransactions();
    }
}
