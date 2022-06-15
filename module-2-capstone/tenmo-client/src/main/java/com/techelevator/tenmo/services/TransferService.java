package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransferService {
    private String BASE_URL;
    private RestTemplate restTemplate= new RestTemplate();
    private AuthenticatedUser user;

    public TransferService(String url, AuthenticatedUser user){
        this.user = user;
        BASE_URL = url;
    }
    public Transfer[] transferList(){
        Transfer[] listOfTransfers = null;
        try{
            listOfTransfers = restTemplate.exchange(BASE_URL + "account/transfers/" + user.getUser().getId(),
                    HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
            System.out.println("-------------------------------------------\n" +
                    "Transfers\n" +
                    "ID          From/To                 Amount\n" +
                    "-------------------------------------------\n");
            String toFromName = "";
            for (Transfer transfers: listOfTransfers){
                if (user.getUser().getId() == transfers.getAccountFrom()){
                    toFromName = "From: " + transfers.getAccountFrom();
                }
                else {
                    toFromName = "To: " + transfers.getAccountTo();
                }
                System.out.println(transfers.getTransferId() + "\t\t" + toFromName + "\t\t$" + transfers.getAmount());
            }
            System.out.println("-------------------------------------------\n" +
                    "Please enter transfer ID to view details (0 to cancel): ");
            Scanner scanner = new Scanner(System.in);
            String input  = scanner.nextLine();
            if(Integer.parseInt(input) != 0){
                boolean transferIdExists = false;
                for(Transfer transfers: listOfTransfers){
                    if(Integer.parseInt(input) == transfers.getTransferId()){
                        Transfer temp = restTemplate.exchange(BASE_URL + "transfers/" + transfers.getTransferId(), HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
                        transferIdExists = true;
                        System.out.println("-------------------------------------------\n"+
                                "Transfer Details\n" +
                                "--------------------------------------------\n" +
                                        " Id: "+ temp.getTransferId() + "\n" +
                                        " From: " + temp.getAccountFrom() + "\n" +
                                        " To: " + temp.getAccountTo() + "\n" +
                                        " Type: " + temp.getTransferTypeId() + "\n" +
                                        " Status: " + temp.getTransferStatusId() + "\n" +
                                        " Amount: $" + temp.getAmount());
                    }
                }
                if(!transferIdExists){
                    System.out.println("Not a valid account ID");
                }
            }
        }catch (Exception e){
            System.out.println("Something went wrong in TransferList!");
        }
        return listOfTransfers;
    }

    public void sendMoney(BigDecimal amount){
        User[] users = null;
        Transfer transfer = new Transfer();
        try {
            Scanner scanner = new Scanner(System.in);
            users = restTemplate.exchange(BASE_URL + "listusers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
            for(User user1 : users){
                if(user1.getId() != user.getUser().getId()){
                    System.out.println(user1.getId() + "          " + user1.getUsername());
                }
            }
            System.out.println("Enter ID of user you are sending to (0 to cancel): ");
            transfer.setAccountTo(Integer.parseInt(scanner.nextLine()));
            transfer.setAccountFrom(user.getUser().getId());
            if()
        }
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
}
