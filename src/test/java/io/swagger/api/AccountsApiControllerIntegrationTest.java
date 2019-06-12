package io.swagger.api;

import io.swagger.model.Account;

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
public class AccountsApiControllerIntegrationTest {

    @Autowired
    private AccountsApi api;

    @Test
    public void createAccountTest() throws Exception {
        Account body = new Account();
        ResponseEntity<String> responseEntity = api.createAccount(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteAccountTest() throws Exception {
        String iban = "iban_example";
        ResponseEntity<Void> responseEntity = api.deleteAccount(iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getAccountByIbanTest() throws Exception {
        String iban = "iban_example";
        ResponseEntity<Account> responseEntity = api.getAccountByIban(iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getAccountsTest() throws Exception {
        String type = "type_example";
        ResponseEntity<List<Account>> responseEntity = api.getAccounts(type);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void toggleAccountStatusTest() throws Exception {
        String iban = "iban_example";
        Account body = new Account();
        ResponseEntity<Void> responseEntity = api.toggleAccountStatus(iban, body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
