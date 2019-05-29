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
        User body = new User();
        ResponseEntity<Integer> responseEntity = api.createUser(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getAccountsByUserIdTest() throws Exception {
        Integer userId = 56;
        ResponseEntity<List<Account>> responseEntity = api.getAccountsByUserId(userId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getUserByIdTest() throws Exception {
        Integer userId = 56;
        ResponseEntity<List<User>> responseEntity = api.getUserById(userId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getUsersTest() throws Exception {
        ResponseEntity<List<User>> responseEntity = api.getUsers();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void loginUserTest() throws Exception {
        User body = new User();
        ResponseEntity<String> responseEntity = api.loginUser(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void logoutUserTest() throws Exception {
        ResponseEntity<Void> responseEntity = api.logoutUser();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
