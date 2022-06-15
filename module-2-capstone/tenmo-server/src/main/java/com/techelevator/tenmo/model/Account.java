package com.techelevator.tenmo.model;

import java.math.BigDecimal;
//Creates account class to be used in Account DAO
public class Account {

    private int accountID;
    private int userID;
    private BigDecimal balance;

    public int getAccountID(){
        return this.accountID;
    }

    public void setAccountID(int accountID){
        this.accountID = accountID;
    }
    public int getUserID(){
        return this.userID;
    }

    public void setUserID(int userID){
        this.userID = userID;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }
}
