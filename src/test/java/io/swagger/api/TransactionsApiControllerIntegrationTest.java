package io.swagger.api;

import io.swagger.Swagger2SpringBoot;
import io.swagger.model.Transaction;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionsApiControllerIntegrationTest {

    /*@LocalServerPort
    private int port;
    private TestRestTemplate template = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();*/

    @Autowired
    private TransactionsApi api;

    //Turn off getAllTransactions() security in TransactionService
    @Test
    public void testGetAllTransactionsShouldRetrieveAListOfAllTransactions() throws ApiException {
        ResponseEntity<List<Transaction>> responseEntity = api.getTransactions(null, null, null, null, null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    //Turn off getTransactionsByFromIban security in TransactionService
    @Test
    public void testGetTransactionByFromIban() throws ApiException{
        ResponseEntity<List<Transaction>> responseEntity = api.getTransactions("NL01INHO0000000004", null, null, null, null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    //Turn off validateTransaction security in TransactionService
    @Test
    public void createTransactionTest() throws ApiException {
        Transaction body = new Transaction("NL01INHO0000000004", "NL01INHO0000000002", 50.0, "TRANSACTION", 1 );
        ResponseEntity<Integer> responseEntity = api.createTransaction(body);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void getTransactionsByType() throws ApiException {
        Transaction.TransactionType transaction = Transaction.TransactionType.TRANSACTION;
        Transaction.TransactionType deposit = Transaction.TransactionType.DEPOSIT;
        Transaction.TransactionType withdrawal = Transaction.TransactionType.WITHDRAWAL;

        ResponseEntity<List<Transaction>> responseEntity = api.getTransactions(null, null, deposit, null, null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    //Turn off getTransactionsByToIban security in TransactionService
    @Test
    public void getTransactionsByToIban() throws ApiException {
        ResponseEntity<List<Transaction>> responseEntity = api.getTransactions(null, "NL01INHO0000000004", null, null, null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    //Turn off getTransactionsByPerformedBy security in TransactionService
    @Test
    public void getTransactionsByPerformedBy() throws ApiException {
        ResponseEntity<List<Transaction>> responseEntity = api.getTransactions(null, null, null, 101, null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
