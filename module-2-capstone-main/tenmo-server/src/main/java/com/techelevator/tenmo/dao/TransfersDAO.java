package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;

import java.math.BigDecimal;
import java.util.List;

public interface TransfersDAO {
    public List<Transfers> getAllTransfers(int userId);
    public Transfers getTransferById(int transactionId);
    public String sendTransfer(int userFrom, int userTo, BigDecimal amount);
    public String updateTransferRequest(Transfers transfer, int statusId);
}
