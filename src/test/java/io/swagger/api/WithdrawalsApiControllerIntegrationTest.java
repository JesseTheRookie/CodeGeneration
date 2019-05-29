package io.swagger.api;

import io.swagger.model.Withdrawal;

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
public class WithdrawalsApiControllerIntegrationTest {

    @Autowired
    private WithdrawalsApi api;

    @Test
    public void creatNewWithdrawalTest() throws Exception {
        Withdrawal body = new Withdrawal();
        ResponseEntity<List<Withdrawal>> responseEntity = api.creatNewWithdrawal(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getAllWithdrawalsTest() throws Exception {
        ResponseEntity<List<Withdrawal>> responseEntity = api.getAllWithdrawals();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getAllWithdrawalsConnectedToSpecifiedAccountTest() throws Exception {
        String iban = "iban_example";
        ResponseEntity<List<Withdrawal>> responseEntity = api.getAllWithdrawalsConnectedToSpecifiedAccount(iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
