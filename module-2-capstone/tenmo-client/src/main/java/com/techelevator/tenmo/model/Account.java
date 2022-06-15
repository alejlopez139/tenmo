package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    private int accountID;
    private int userID;

    public int getAccountID() {
        return this.accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

