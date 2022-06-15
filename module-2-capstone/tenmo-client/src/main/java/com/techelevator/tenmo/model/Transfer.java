package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    long transferId;
    long transferTypeId;
    String transferType;
    long transferStatusId;
    String transferStatus;
    long accountFrom;
    long accountTo;
    BigDecimal amount;

    public Transfer() {
    }

    public Transfer(long transferId, long transferTypeId, String transferType, long transferStatusId, String transferStatus, long accountFrom, long accountTo, BigDecimal amount) {
        this.transferId = transferId;
        this.transferTypeId = transferTypeId;
        this.transferType = transferType;
        this.transferStatusId = transferStatusId;
        this.transferStatus = transferStatus;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.transferStatus = getTransferStatusById(transferStatusId);
        this.transferType = getTransferTypeById(transferTypeId);
    }

    public long getTransferId() {
        return transferId;
    }

    public void setTransferId(long transferId) {
        this.transferId = transferId;
    }

    public long getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(long transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public long getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(long transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransferStatusById(long transferStatusId){
        if (transferStatusId == 1){
            return "Pending";
        } else if (transferStatusId == 2){
            return "Approved";
        } else{
            return "Rejected";
        }
    }

    public String getTransferTypeById(long transferTypeId){
        if (transferTypeId == 1){
            return "Request";
        }
        else {
            return "Send";
        }
    }
}
