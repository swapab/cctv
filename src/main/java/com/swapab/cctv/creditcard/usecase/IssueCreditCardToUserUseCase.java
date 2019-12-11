package com.swapab.cctv.creditcard.usecase;

import com.swapab.cctv.creditcard.domain.CreditCard;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IssueCreditCardToUserUseCase {
    private final DoesUserExists doesUserExists;
    private final ValidateCreditCardUseCase validateCreditCardUseCase;
    private final SaveCreditCard saveCreditCard;

    public IssueCreditCardToUserUseCase(DoesUserExists doesUserExists, ValidateCreditCardUseCase validateCreditCardUseCase, SaveCreditCard saveCreditCard) {
        this.doesUserExists = doesUserExists;
        this.validateCreditCardUseCase = validateCreditCardUseCase;
        this.saveCreditCard = saveCreditCard;
    }

    public CreditCard assign(String userId, String creditCardNumber) {
        if(!doesUserExists.doesUserExists(userId)) {
            throw new UserDoesNotExistsException();
        }
        if (!validateCreditCardUseCase.isCreditCardNumberValid(creditCardNumber)) {
            throw new InvalidCreditCardException();
        }
        CreditCard newCreditCard = new CreditCard(
                UUID.randomUUID().toString(),
                userId,
                creditCardNumber
        );

        if(!saveCreditCard.saveCreditCardForUser(newCreditCard)){
            throw new CreditCardAlreadyExistsException();
        }

        return newCreditCard;
    }
}
