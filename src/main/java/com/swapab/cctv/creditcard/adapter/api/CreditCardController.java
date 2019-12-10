package com.swapab.cctv.creditcard.adapter.api;

import com.swapab.cctv.creditcard.adapter.api.dto.CreditCardRequestDTO;
import com.swapab.cctv.creditcard.adapter.api.exceptions.BadRequestError;
import com.swapab.cctv.creditcard.adapter.api.exceptions.NotFoundError;
import com.swapab.cctv.creditcard.domain.CreditCard;
import com.swapab.cctv.creditcard.usecase.CreditCardAlreadyExistsException;
import com.swapab.cctv.creditcard.usecase.InvalidCreditCardException;
import com.swapab.cctv.creditcard.usecase.IssueCreditCardToUserUserCase;
import com.swapab.cctv.creditcard.usecase.UserDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/creditCards")
public class CreditCardController {

    private final IssueCreditCardToUserUserCase issueCreditCardToUserUserCase;

    public CreditCardController(IssueCreditCardToUserUserCase issueCreditCardToUserUserCase) {
        this.issueCreditCardToUserUserCase = issueCreditCardToUserUserCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard issueCreditCard(@RequestBody CreditCardRequestDTO creditCardRequestDTO, @PathVariable String userId) {
        try {
            return issueCreditCardToUserUserCase.assign(userId, creditCardRequestDTO.getCreditCardNumber());
        } catch (UserDoesNotExistsException e) {
            throw new NotFoundError(e.getMessage());
        } catch (InvalidCreditCardException | CreditCardAlreadyExistsException e) {
            throw new BadRequestError(e.getMessage());
        }
    }
}
