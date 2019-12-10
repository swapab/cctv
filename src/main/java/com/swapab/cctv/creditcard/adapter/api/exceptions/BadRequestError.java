package com.swapab.cctv.creditcard.adapter.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestError extends RuntimeException {
    public BadRequestError(String message) {
        super(message);
    }
}
