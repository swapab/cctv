package com.swapab.cctv.creditcard.api;

import com.swapab.cctv.creditcard.api.dto.CreditCardRequestDTO;
import com.swapab.cctv.creditcard.api.exceptions.BadRequestError;
import com.swapab.cctv.creditcard.api.exceptions.NotFoundError;
import com.swapab.cctv.creditcard.domain.CreditCard;
import com.swapab.cctv.creditcard.usecase.CreditCardAlreadyExistsException;
import com.swapab.cctv.creditcard.usecase.InvalidCreditCardException;
import com.swapab.cctv.creditcard.usecase.IssueCreditCardToUserUseCase;
import com.swapab.cctv.creditcard.usecase.UserDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/creditCards")
public class CreditCardController {

    private final IssueCreditCardToUserUseCase issueCreditCardToUserUseCase;

    public CreditCardController(IssueCreditCardToUserUseCase issueCreditCardToUserUseCase) {
        this.issueCreditCardToUserUseCase = issueCreditCardToUserUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard issueCreditCard(@RequestBody CreditCardRequestDTO creditCardRequestDTO, @PathVariable String userId) {
        try {
            return issueCreditCardToUserUseCase.assign(userId, creditCardRequestDTO.getCreditCardNumber());
        } catch (UserDoesNotExistsException e) {
            throw new NotFoundError(e.getMessage());
        } catch (InvalidCreditCardException | CreditCardAlreadyExistsException e) {
            throw new BadRequestError(e.getMessage());
        }
    }
}
