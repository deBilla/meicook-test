package com.dimuthu.bankAccount.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AgeException extends RuntimeException {
    public AgeException(String errorMsg) {
        super(errorMsg);
    }
}
