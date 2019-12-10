package com.swapab.cctv.creditcard.datastore;

import com.swapab.cctv.creditcard.domain.CreditCard;
import com.swapab.cctv.creditcard.usecase.SaveCreditCard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CreditCardDataStoreProvider implements SaveCreditCard {

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

}
