package com.dimuthu.bankAccount.exceptions;

public class AccountBalanceInsufficientException extends RuntimeException {
    public AccountBalanceInsufficientException(String message) {
        super(message);
    }
}
