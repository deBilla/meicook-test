package com.dimuthu.bankAccount.entitities;

import lombok.Data;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Account {
    private User user;
    private BigDecimal balance;
    private String accountCurrency;
    private UUID uuid;
}