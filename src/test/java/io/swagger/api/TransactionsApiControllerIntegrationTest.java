package io.swagger.api;

import io.swagger.model.Transaction;

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
public class TransactionsApiControllerIntegrationTest {

    @Autowired
    private TransactionsApi api;

    @Test
    public void createTransactionTest() throws Exception {
        Transaction body = new Transaction();
        ResponseEntity<Integer> responseEntity = api.createTransaction(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getTransactionsTest() throws Exception {
        Integer transactionID = 56;
        String from = "from_example";
        String to = "to_example";
        Integer performedBy = 56;
        ResponseEntity<List<Transaction>> responseEntity = api.getTransactions(transactionID, from, to, performedBy);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
