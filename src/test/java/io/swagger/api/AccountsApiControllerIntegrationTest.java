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
//String iban, User user, String name, Double balance, String accounttype, String status

    //Turn off createAccountSecurity to use test
    @Test
    public void createAccountTest() throws Exception {
        User Willem = new User("Willem", "wachtwoord123", "EMPLOYEE");
        userRepository.save(Willem);
        Account body = new Account("NL01INHO0000000006", Willem, "jan", 100.00, "CURRENT", "ACTIVE" );
        ResponseEntity<String> responseEntity = api.createAccount(body);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteAccountSuccesShouldReturn200() throws Exception {
        String accountNumber = "NL01INHO0000000004";
        ResponseEntity<Void> responseEntity = api.deleteAccount(accountNumber);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void deleteNonExistingAccountShouldReturn404() throws Exception {
        String accountNumber = "NL01INHO0000000100";
        ResponseEntity<Void> responseEntity = api.deleteAccount(accountNumber);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
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
        ResponseEntity<Void> responseEntity = api.toggleAccountStatus(iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
