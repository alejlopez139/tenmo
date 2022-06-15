package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JDBCTransferDAO implements TransferDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public List<Transfer> getAllTransfers(int userId) {
        String sql = "SELECT * FROM transfers";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Transfer> transferList = new ArrayList<>();

        try {
            while (results.next()){
                transferList.add(mapResultToTransfer(results));
            }
        }catch (DataAccessException e) {
            System.out.println("Error accessing getAllTransfers");
        }
        return transferList;
    }

    @Override
    public List<Transfer> getTransferByID(int transferId) {
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
                "FROM transfers " +
                "JOIN accounts ON accounts.account_id = transfers.account_from OR accounts.account_id = transfers.account_to " +
                "WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
        List<Transfer> transfer = new ArrayList<>();

        try {
            if(result.next()){
                transfer.add(mapResultToTransfer(result));
            }
        }catch (DataAccessException e) {
            System.out.println("Error accessing getTransferById");
        }

        return transfer;
    }


    @Override
    public String Transaction(int userFrom, int userTo, BigDecimal amount) {
        if(userFrom == userTo){
            return "You cannot send money to yourself!";
        }
        if(amount.compareTo(accountDAO.getBalance(userFrom)) == -1 && amount.compareTo(new BigDecimal(0)) == 1){
            String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                    "VALUES (2, 2, ?, ?, ?)";
            jdbcTemplate.update(sql, userFrom, userTo, amount);
            accountDAO.addToBalance(amount, userTo);
            accountDAO.subtractFromBalance(amount, userFrom);
            return "Transfer success!";
        }else{
            return "Transfer failed!";
        }

    }

    private Transfer mapResultToTransfer(SqlRowSet result) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(result.getInt("transfer_id"));
        transfer.setTransferType(result.getInt("transfer_type_id"));
        transfer.setTransferStatus(result.getInt("transfer_status_id"));
        transfer.setFromAccount(result.getInt("account_from"));
        transfer.setToAccount(result.getInt("account_to"));
        transfer.setAmount(result.getBigDecimal("amount"));

        return transfer;
    }
}
