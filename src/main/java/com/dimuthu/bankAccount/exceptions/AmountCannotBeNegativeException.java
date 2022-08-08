package com.dimuthu.bankAccount.exceptions;

public class AmountCannotBeNegativeException extends RuntimeException{
    public AmountCannotBeNegativeException(String message) {
        super(message);
    }
}
