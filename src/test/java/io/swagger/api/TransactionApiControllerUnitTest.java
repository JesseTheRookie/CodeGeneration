package io.swagger.api;

import io.swagger.Swagger2SpringBoot;
import io.swagger.model.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionApiControllerUnitTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate template = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void testGetAllTransactionsShouldRetrieveAListOfAllTransactions() throws JSONException{
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

        for(int i = 0; i < array.length();i++ ){

        }
       // JSONAssert.assertEquals(expected, response.getBody(), true);
    }
    private String createFullUrl(String uri) {
        return "http://localhost:" + port + uri;
    }
}
