package com.dimuthu.bankAccount.exceptions;

public class NotSupportedCurrencyException extends RuntimeException {
    public NotSupportedCurrencyException(String errorMsg) {
        super(errorMsg);
    }
}
