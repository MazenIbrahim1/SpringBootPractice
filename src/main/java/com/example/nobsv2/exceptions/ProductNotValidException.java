package com.example.nobsv2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotValidException extends RuntimeException {
    public ProductNotValidException(String message) {
        super(ErrorMessages.PRODUCT_NOT_VALID.getMessage() + ": " + message);
    }
}
