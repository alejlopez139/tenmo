package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class RestUserService implements UserService{

    private RestTemplate restTemplate;
    private final String Base_URL = "http://localhost:8080/";

    public RestUserService(){
        this.restTemplate = new RestTemplate();
    }

    @Override
    public User[] getAllUsers(AuthenticatedUser authenticatedUser){
        User[] users = null;
        try {
            users = restTemplate.exchange(Base_URL + "users", HttpMethod.GET, makeEntity(authenticatedUser), User[].class).getBody();
        }catch (RestClientException e) {
            System.out.println("Error in getAllUsers!");
        }
        return users;
    }

    @Override
    public User getUserByUserId(AuthenticatedUser authenticatedUser, int id) {
        User user = null;
        try {
            user = restTemplate.exchange(Base_URL + "user/" + id, HttpMethod.GET, makeEntity(authenticatedUser), User.class).getBody();
    }catch (RestClientException e){
            System.out.println("Error in getUSerByUSerID");
        }


    }

    private HttpEntity makeEntity(AuthenticatedUser authenticatedUser){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(authenticatedUser.getToken());
        HttpEntity entity = new HttpEntity(httpHeaders);
        return entity;
    }
}
