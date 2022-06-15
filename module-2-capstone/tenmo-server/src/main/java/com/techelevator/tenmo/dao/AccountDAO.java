package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

//Created Interface for AccountDAO, will be overrided when used by JDBC class
public interface AccountDAO{
    BigDecimal getBalance(int userID);
    BigDecimal addToBalance(BigDecimal amountToAdd, int id);
    BigDecimal subtractFromBalance(BigDecimal amountToSubtract, int id);
    Account findUserByID(int userID);
    public Account findAccountById(int id);
}