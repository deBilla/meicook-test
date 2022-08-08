package com.dimuthu.bankAccount.entitities;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Transaction {
    private UUID uuid;

    @NonNull
    private UUID accountUuid;
    private BigDecimal withdrawAmount = BigDecimal.valueOf(0);
    private BigDecimal depositAmount = BigDecimal.valueOf(0);
    private BigDecimal balance = BigDecimal.valueOf(0);

    public Transaction(UUID accountUuid, BigDecimal withdrawAmount, BigDecimal depositAmount) {
        this.accountUuid = accountUuid;
        this.withdrawAmount = withdrawAmount;
        this.depositAmount = depositAmount;
        this.uuid = UUID.randomUUID();
    }
}
