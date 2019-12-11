package com.swapab.cctv.creditcard.datastore;

import com.swapab.cctv.creditcard.domain.CreditCard;
import com.swapab.cctv.creditcard.usecase.SaveCreditCard;
import com.swapab.cctv.transaction.usecase.TransactionDomainGetCreditCardForUser;

import java.util.*;

public class CreditCardDataStoreProvider implements SaveCreditCard, TransactionDomainGetCreditCardForUser {

    private Map<String, Set<CreditCard>> userCreditCards;

    public CreditCardDataStoreProvider(HashMap<String, Set<CreditCard>> userCreditCards) {
        this.userCreditCards = userCreditCards;
    }

    @Override
    public boolean saveCreditCardForUser(CreditCard creditCard) {
        if(this.userCreditCards.containsKey(creditCard.getUserId())){
            return this.userCreditCards.get(creditCard.getUserId()).add(creditCard);
        } else {
            HashSet<CreditCard> creditCards = new HashSet<>();
            creditCards.add(creditCard);
            this.userCreditCards.put(creditCard.getUserId(), creditCards);
        }
        return true;
    }

    @Override
    public Optional<CreditCard> getCreditCardByUserIdAndCreditCardId(String userId, String cardId) {
        if(this.userCreditCards.containsKey(userId)){
            return this.userCreditCards.get(userId)
                    .stream()
                    .filter(
                            creditCard -> creditCard.getCreditCardId().equals(cardId)
                    ).findFirst();
        }
        return Optional.empty();
    }
}
