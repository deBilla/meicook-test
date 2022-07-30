package com.dimuthu.bankAccount.entitities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Transaction {
    private UUID uuid;
    private UUID accountUuid;
    private BigDecimal withdrawAmount;
    private BigDecimal depositAmount;
}
