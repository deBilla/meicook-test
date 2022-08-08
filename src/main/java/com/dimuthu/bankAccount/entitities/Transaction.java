package com.dimuthu.bankAccount.entitities;

import com.dimuthu.bankAccount.exceptions.AmountCannotBeNegativeException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private UUID uuid;

    @NonNull
    private UUID accountUuid;
    private BigDecimal withdrawAmount = BigDecimal.valueOf(0);
    private BigDecimal depositAmount = BigDecimal.valueOf(0);
    private BigDecimal balance = BigDecimal.valueOf(0);

    public Transaction(UUID accountUuid, BigDecimal withdrawAmount, BigDecimal depositAmount) {
        this.accountUuid = accountUuid;

        if (withdrawAmount.compareTo(BigDecimal.valueOf(0)) < 0 || depositAmount.compareTo(BigDecimal.valueOf(0)) < 0) throw new AmountCannotBeNegativeException("Amount Cannot be negative");

        this.withdrawAmount = withdrawAmount;
        this.depositAmount = depositAmount;
        this.uuid = UUID.randomUUID();
    }

    public Transaction() {
        this.uuid = UUID.randomUUID();
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        if (withdrawAmount.compareTo(BigDecimal.valueOf(0)) < 0) throw new AmountCannotBeNegativeException("Amount Cannot be negative");

        this.withdrawAmount = withdrawAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        if (depositAmount.compareTo(BigDecimal.valueOf(0)) < 0) throw new AmountCannotBeNegativeException("Amount Cannot be negative");

        this.depositAmount = depositAmount;
    }
}
