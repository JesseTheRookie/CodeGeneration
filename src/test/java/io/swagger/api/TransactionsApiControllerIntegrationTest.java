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
@SpringBootTest(classes = Swagger2SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionsApiControllerIntegrationTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate template = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Autowired
    private TransactionsApi api;

    @Test
    public void testGetAllTransactionsShouldRetrieveAListOfAllTransactions() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = template.exchange(
                createFullUrl("/transactions"),
                HttpMethod.GET, entity, String.class);

        JSONArray array = new JSONArray(response.getBody());
        Assert.assertTrue(array.length() >= 1);
    }

    @Test
    public void testGetTransactionByFromIban() throws JSONException{
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = template.exchange(
                createFullUrl("/transactions?fromIban=NL01INHO0000000004"),
                HttpMethod.GET, entity, String.class);

        JSONArray array = new JSONArray(response.getBody());
        //trying to verify if all transactions are from NL01INHO0000000004
        for(int i = 0; i < array.length();i++ ){

        }
        // JSONAssert.assertEquals(expected, response.getBody(), true);
    }
    private String createFullUrl(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void createTransactionTest() throws Exception {
        Transaction body = new Transaction();
        ResponseEntity<Integer> responseEntity = api.createTransaction(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getTransactionsTest() throws Exception {
        //Integer transactionID = 56;
        String from = "from_example";
        String to = "to_example";
        Transaction.TransactionType Type = Transaction.TransactionType.TRANSACTION;
        Integer performedBy = 56;
        ResponseEntity<List<Transaction>> responseEntity = api.getTransactions( from, to, Type, performedBy);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
