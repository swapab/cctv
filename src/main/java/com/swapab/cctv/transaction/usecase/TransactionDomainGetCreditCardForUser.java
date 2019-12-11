package com.swapab.cctv.transaction.usecase;

import com.swapab.cctv.creditcard.domain.CreditCard;

import java.util.Optional;

public interface TransactionDomainGetCreditCardForUser {
    Optional<CreditCard> getCreditCardByUserIdAndCreditCardId(String userId, String cardId);
}
