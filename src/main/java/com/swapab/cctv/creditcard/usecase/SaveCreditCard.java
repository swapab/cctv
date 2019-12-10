package com.swapab.cctv.creditcard.usecase;

import com.swapab.cctv.creditcard.domain.CreditCard;

public interface SaveCreditCard {
    boolean saveCreditCardForUser(CreditCard creditCard);
}
