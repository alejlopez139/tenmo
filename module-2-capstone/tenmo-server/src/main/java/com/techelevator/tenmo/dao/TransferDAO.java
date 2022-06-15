package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDAO {

    public List<Transfer> getAllTransfers(int userId);
    public List<Transfer> getTransferByID(int transferId);
    public String Transaction(int userFrom, int userTo, BigDecimal amount);



}
