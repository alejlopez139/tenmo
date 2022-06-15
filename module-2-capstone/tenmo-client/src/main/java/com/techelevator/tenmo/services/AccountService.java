package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.*;

import java.math.BigDecimal;

//Class to get the balance of an Account
//Is it possible to not use an entire class or to refactor into an existing class or put in more methods here?
//Maybe it's better to have each class do one specific thing?

public class AccountService {

    private String BALANCE_URL;
    private String USER_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    //Constructor
    public AccountService(String url, AuthenticatedUser currentUser){
        this.currentUser = currentUser;
        this.BALANCE_URL = url +"balance/";
    }
    //Used from previous Exercises. Used to make restTemplate.exchange easier to use.
    private HttpEntity makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }
    //Returns current user's balance.
    public BigDecimal getBalance(){
        BigDecimal balance = new BigDecimal(0);

        try{
            balance = restTemplate.exchange(BALANCE_URL + currentUser.getUser().getId(), HttpMethod.GET,
                    makeAuthEntity(), BigDecimal.class).getBody();
            System.out.println("Your current account balance is: $" + balance);
        } catch (RestClientException e){
            System.out.println("Error getting balance");
        }
        return balance;
    }

    // adds given amount to given user's balance
    public BigDecimal addToBalance(BigDecimal addAmount, long userId){
        restTemplate.put(BALANCE_URL + userId, HttpMethod.PUT, makeAuthEntity(),
                getBalance().add(addAmount));
        return getBalance();
    }
    // subtracts given amount to given user's balance
    public BigDecimal subtractFromBalance(BigDecimal subtractAmount, long userId){
        restTemplate.put(BALANCE_URL + userId, HttpMethod.PUT, makeAuthEntity(),
                getBalance().subtract(subtractAmount));
        return getBalance();
    }

    public Account findUserbyID(int userID) {
//        restTemplate.exchange()
        return null;
    }

    public Account findAccountById(int id) {
        return null;
    }


}