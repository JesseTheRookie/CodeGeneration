package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.User;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersApiControllerIntegrationTest {

    @Autowired
    private UsersApi api;


    @Test
    public void createUserTest() throws Exception {
        User Erwin = new User("Erwin", "wachtwoord123", "EMPLOYEE");
        ResponseEntity<Integer> responseEntity = api.createUser(Erwin);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void getAccountsByUserIdTest() throws Exception {
        Integer userId = 101;
        ResponseEntity<List<Account>> responseEntity = api.getAccountsByUserId(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getUserByIdTestShouldReturn200() throws Exception {
        //can't test if Employee is logged in
        Integer userId = 101;
        ResponseEntity<User> responseEntity = api.getUserById(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserByIdTestShouldReturn400() throws Exception {
        //can't test if Employee is logged in
        Integer userId = -1;
        ResponseEntity<User> responseEntity = api.getUserById(userId);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserByIdTestShouldReturn404() throws Exception {
        //can't test if Employee is logged in
        Integer userId = 99999;
        ResponseEntity<User> responseEntity = api.getUserById(userId);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserByIdTestShouldReturn405() throws Exception {
        //return 405 if user is not logged in
        Integer userId = 102;
        ResponseEntity<User> responseEntity = api.getUserById(userId);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.getStatusCode());
    }

    @Test
    public void getUsersTest() throws Exception {
        //can't test if Employee is logged in
        ResponseEntity<List<User>> responseEntity = api.getUsers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


/*
    @Test
    public void loginUserTest() throws Exception {
        User body = new User("TheoTest", "test123", "USER");
        ResponseEntity<String> responseEntity = api.loginUser(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }
    @Test
    public void logoutUserTest() throws Exception {
        ResponseEntity<Void> responseEntity = api.logoutUser();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }
*/
}
