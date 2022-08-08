package com.dimuthu.bankAccount.entitities;

import com.dimuthu.bankAccount.exceptions.NotSupportedCurrencyException;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Set;
import java.util.UUID;

@Data
public class Account {
    @NonNull
    private User user;

    private BigDecimal balance = BigDecimal.valueOf(0);

    @NonNull
    private String accountCurrency;

    @NonNull
    private UUID uuid;

    public void setAccountCurrency(String accountCurrency) {
        try {
            Currency accCurrency = Currency.getInstance(accountCurrency);
            this.accountCurrency = accCurrency.getCurrencyCode();
        } catch (IllegalArgumentException e) {
            throw new NotSupportedCurrencyException("Currency not supported");
        }
    }

    public Account(User user, String accountCurrency, UUID uuid) {
        this.user = user;

        try {
            Currency accCurrency = Currency.getInstance(accountCurrency);
            this.accountCurrency = accCurrency.getCurrencyCode();
        } catch (IllegalArgumentException e) {
            throw new NotSupportedCurrencyException("Currency not supported");
        }

        this.uuid = uuid;
    }
}