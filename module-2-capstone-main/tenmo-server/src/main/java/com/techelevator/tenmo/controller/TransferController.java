package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransfersDAO;
import com.techelevator.tenmo.model.Transfers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    @Autowired
    private TransfersDAO transfersDAO;

    @RequestMapping(value = "account/transfers/{id}", method = RequestMethod.GET)
    public List<Transfers> getAllMyTransfers(@PathVariable int id) {
        List<Transfers> output = transfersDAO.getAllTransfers(id);
        return output;
    }

    @RequestMapping(path = "transfers/{id}", method = RequestMethod.GET)
    public Transfers getSelectedTransfer(@PathVariable int id) {
        Transfers transfer = transfersDAO.getTransferById(id);
        return transfer;
    }

    @RequestMapping(path = "transfers", method = RequestMethod.POST)
    public String sendTransferRequest(@RequestBody Transfers transfer) {
        String results = transfersDAO.sendTransfer(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
        return results;
    }


}