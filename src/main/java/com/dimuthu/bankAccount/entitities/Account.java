package com.dimuthu.bankAccount.entitities;

import com.dimuthu.bankAccount.exceptions.NotSupportedCurrencyException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.id")
    private User user;

    private BigDecimal balance = BigDecimal.valueOf(0);

    @NonNull
    private String accountCurrency;

    @Id
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

    public Account(User user, String accountCurrency) {
        this.user = user;

        try {
            Currency accCurrency = Currency.getInstance(accountCurrency);
            this.accountCurrency = accCurrency.getCurrencyCode();
        } catch (IllegalArgumentException e) {
            throw new NotSupportedCurrencyException("Currency not supported");
        }

        this.uuid = UUID.randomUUID();
    }

    public Account() {
        this.uuid = UUID.randomUUID();
    }
}