package io.swagger.api;

import io.swagger.model.Account;

import java.util.*;

import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import io.swagger.service.UserService;
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

    @Autowired
    private UserRepository userRepository;

    //Turn off createAccount security in AccountService to use test
    @Test
    public void createAccountTest() throws Exception {
        User Willem = new User("Willem", "wachtwoord123", "EMPLOYEE");
        userRepository.save(Willem);
        Account body = new Account("NL01INHO0000000006", Willem, "jan", 100.00, "CURRENT", "ACTIVE" );
        ResponseEntity<String> responseEntity = api.createAccount(body);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    //Turn off deleteAccount security in AccountService to use test
    @Test
    public void deleteAccountSuccesShouldReturn200() throws Exception {
        String accountNumber = "NL01INHO0000000004";
        ResponseEntity<Void> responseEntity = api.deleteAccount(accountNumber);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    //Turn off deleteAccount security in AccountService to use test
    @Test(expected = IllegalArgumentException.class)
    public void deleteNonExistingAccountShouldReturn404() throws Exception {
        String accountNumber = "NL01INHO0000000100";
        ResponseEntity<Void> responseEntity = api.deleteAccount(accountNumber);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    //Turn off getAccountsByIbanTest security in AccountService to use test
    @Test
    public void getAccountByIbanTest() throws Exception {
        String iban = "NL01INHO0000000004";
        ResponseEntity<Account> responseEntity = api.getAccountByIban(iban);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    //Turn off getAllAccountsByType(type) security in AccountService to use test
    @Test
    public void getAccountsTestByType() throws Exception {
        String typeCurrent = "CURRENT";
        String typeSavings = "SAVINGS";
        ResponseEntity<List<Account>> responseEntity = api.getAccounts(typeCurrent);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    //Turn off getAllAccountsByType() security in AccountService to use test
    @Test
    public void getAllAccountsTestByType() throws Exception {
        ResponseEntity<List<Account>> responseEntity = api.getAccounts(null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    //Turn off toggleAccountStatus security in AccountService to use test
    @Test
    public void toggleAccountStatusTest() throws Exception {
        String iban = "NL01INHO0000000004";
        ResponseEntity<Void> responseEntity = api.toggleAccountStatus(iban);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
