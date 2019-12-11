package com.swapab.cctv.transaction.usecase;

import com.swapab.cctv.transaction.domain.Transaction;
import com.swapab.cctv.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class IssueCardTransactionUseCase {
    private final TransactionDomainGetUserUseCase transactionDomainGetUserUseCase;
    private final TransactionDomainDeductMoneyFromUserUseCase transactionDomainDeductMoneyFromUserUseCase;
    private final CreateNewTransactionForCard createNewTransactionForCard;
    private final TransactionDomainGetCreditCardForUser transactionDomainGetCreditCardForUser;

    public IssueCardTransactionUseCase(
            TransactionDomainGetUserUseCase transactionDomainGetUserUseCase,
            TransactionDomainDeductMoneyFromUserUseCase transactionDomainDeductMoneyFromUserUseCase,
            CreateNewTransactionForCard createNewTransactionForCard,
            TransactionDomainGetCreditCardForUser transactionDomainGetCreditCardForUser) {
        this.transactionDomainGetUserUseCase = transactionDomainGetUserUseCase;
        this.transactionDomainDeductMoneyFromUserUseCase = transactionDomainDeductMoneyFromUserUseCase;
        this.createNewTransactionForCard = createNewTransactionForCard;
        this.transactionDomainGetCreditCardForUser = transactionDomainGetCreditCardForUser;
    }

    public void issueTransaction(String userId, String creditCardId, double amount) {
        User user = transactionDomainGetUserUseCase.getUser(userId);
        transactionDomainGetCreditCardForUser
                .getCreditCardByUserIdAndCreditCardId(userId, creditCardId)
                .orElseThrow(CardNotFoundException::new);

        if(user.getBalance() < amount) {
            throw new InSufficientBalanceException();
        }

        transactionDomainDeductMoneyFromUserUseCase.addMoney(userId, -amount);
        createNewTransactionForCard.saveTransaction(new Transaction(creditCardId, amount));
    }
}
