package io.swagger.api;

import io.swagger.model.Deposit;

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
public class DepositsApiControllerIntegrationTest {

    @Autowired
    private DepositsApi api;

    @Test
    public void createDepositTest() throws Exception {
        Deposit body = new Deposit();
        ResponseEntity<Void> responseEntity = api.createDeposit(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getDepositsTest() throws Exception {
        ResponseEntity<List<Deposit>> responseEntity = api.getDeposits();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getDepositsByIbanTest() throws Exception {
        String iban = "iban_example";
        ResponseEntity<List<Deposit>> responseEntity = api.getDepositsByIban(iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
