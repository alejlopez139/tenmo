package com.techelevator.tenmo.controller;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
@RestController
//CAn only use methods if user is authenticated
@PreAuthorize("isAuthenticated()")
public class AccountController {
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private UserDao userDAO;
    @Autowired
    private TransferDAO transferDAO;


    //Constructor
    public AccountController(AccountDAO accountDAO, UserDao userDAO) {
        this.accountDAO = accountDAO;
        this.userDAO = userDAO;
    }

    //is called when URL ends with balance/{id}, whatever id is called will send balance of that user
    @RequestMapping(path = "balance/{id}", method = RequestMethod.GET)
    //returns balance from the account
    public BigDecimal getBalance(@PathVariable int id) {
        BigDecimal balance = accountDAO.getBalance(id);
        return balance;
    }

    @RequestMapping(path = "account/transfers/{id}", method = RequestMethod.GET)
    public List<Transfer> getMyTransfers(@PathVariable int id){
        List<Transfer> listOfTransfers = transferDAO.getAllTransfers(id);
        return listOfTransfers;
    }

    @RequestMapping(path="transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int id){
        Transfer transfer = (Transfer) transferDAO.getTransferByID(id);
        return transfer;
    }


}