package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int transferId;
    private int transferType;
    private int transferStatus;
    private int FromAccount;
    private int ToAccount;
    private BigDecimal amount;

    public Transfer(){

    }

    public Transfer(int transferId, int transferType, int transferStatus, int FromAccount, int ToAccount, BigDecimal amount) {
        this.transferId = transferId;
        this.transferType = transferType;
        this.transferStatus = transferStatus;
        this.FromAccount = FromAccount;
        this.ToAccount = ToAccount;
        this.amount = amount;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }

    public int getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(int transferStatus) {
        this.transferStatus = transferStatus;
    }

    public int getFromAccount() {
        return FromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.FromAccount = fromAccount;
    }

    public int getToAccount() {
        return ToAccount;
    }

    public void setToAccount(int toAccount) {
        this.ToAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
