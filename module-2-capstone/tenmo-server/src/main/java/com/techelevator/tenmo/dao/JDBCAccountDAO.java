package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.tenmo.model.Account;

@Service
//Inherits from the interface of AccountDAO and overrides methods
public class JDBCAccountDAO implements AccountDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    //Default Constructor
    public JDBCAccountDAO(){}
    //Constructor that assigns jdbcTemplate
    public JDBCAccountDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    //Uses SQL statement to access table and return balance. Returns an error if an exception occurs when accessing data
    @Override
    public BigDecimal getBalance(int id) {
        String sql = "select balance from account where user_id = ?";
        BigDecimal balance = null;
        try {
            balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, id);
        }catch (DataAccessException e) {
            System.out.println("Error accessing balance data");
        }
        return balance;
    }
    //Might be used for transferring balance?
    @Override
    public BigDecimal addToBalance(BigDecimal amountToAdd, int id) {
        String sql = "UPDATE account SET balance = ? WHERE user_id = ?;";
        BigDecimal balance = null;
        try {
            balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, getBalance(id).add(amountToAdd), id);
        } catch (DataAccessException e){
            System.out.println("Error addToBalance");
        }

        return null;
    }
    //might be used for transferring balance?
    @Override
    public BigDecimal subtractFromBalance(BigDecimal amountToSubtract, int id) {
        String sql = "UPDATE account SET balance = ? WHERE user_id = ?;";
        BigDecimal balance = null;
        try {
            balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, getBalance(id).subtract(amountToSubtract), id);
        } catch (DataAccessException e){
            System.out.println("Error subtractFromBalance");
        }
        return balance;
    }
    //Might need it to transfer balance
    @Override
    public Account findUserByID(int id) {
        String sql = "SELECT account_id, user_id, balance FROM account a " +
                "JOIN tenmo_user tu ON tu.user_id = a.user_id" +
                "WHERE user_id = ?;";
        Account account = null;
        try{
            account = jdbcTemplate.queryForObject(sql, Account.class, id);

        }catch (DataAccessException e){
        System.out.println("Error accessing FindUserByID");
    }
        return null;
    }
    //might need it to transfer balance
    @Override
    public Account findAccountById(int id) {
        String sql = "SELECT account_id, user_id, balance FROM account a " +
                "JOIN tenmo_user tu ON tu.user_id = a.user_id" +
                "WHERE account_id = ?;";
        Account account = null;
        try{
            account = jdbcTemplate.queryForObject(sql, Account.class, id);

        }catch (DataAccessException e){
            System.out.println("Error accessing findAccountByID");
        }
        return null;
    }
}