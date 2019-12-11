package com.swapab.cctv.transaction.api;

import com.swapab.cctv.creditcard.api.exceptions.NotFoundError;
import com.swapab.cctv.transaction.api.dto.TransactionRequestDTO;
import com.swapab.cctv.transaction.api.exceptions.ForbiddenError;
import com.swapab.cctv.transaction.usecase.CardNotFoundException;
import com.swapab.cctv.transaction.usecase.InSufficientBalanceException;
import com.swapab.cctv.transaction.usecase.IssueCardTransactionUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/transactions")
public class TransactionController {

    private final IssueCardTransactionUseCase issueCardTransactionUseCase;

    public TransactionController(IssueCardTransactionUseCase issueCardTransactionUseCase) {
        this.issueCardTransactionUseCase = issueCardTransactionUseCase;
    }

    @PostMapping
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
}
