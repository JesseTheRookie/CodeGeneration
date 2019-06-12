package io.swagger.api;

import io.swagger.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.SwaggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-29T12:43:24.827Z[GMT]")
@Controller
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private SwaggerService service;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request, SwaggerService service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    // Werkt
    public ResponseEntity<String> createAccount(@ApiParam(value = "Account details" ,required=true )  @Valid @RequestBody Account body) {
        String accept = request.getHeader("Accept");
        service.createAccount(body);
        return new ResponseEntity<String>(String.valueOf(body.getIban()), HttpStatus.CREATED);
    }

    // Werkt
    public ResponseEntity<Void> deleteAccount(@ApiParam(value = "The iban",required=true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        service.deleteAccount(iban);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // Werkt
    public ResponseEntity<Account> getAccountByIban(@ApiParam(value = "The iban",required=true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Account>(service.getAccountByIban(iban),HttpStatus.OK);
    }

    // Werkt
    // ToDo optionele variabele doen nu nog niets
    public ResponseEntity<List<Account>> getAccounts(@ApiParam(value = "The type of accounts that need to be considered to filter", allowableValues = "current, savings") @Valid @RequestParam(value = "type", required = false) String type) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Account>>((List<Account>)service.getAllAccounts(), HttpStatus.OK);
    }


    public ResponseEntity<Void> toggleAccountStatus(@ApiParam(value = "The iban",required=true) @PathVariable("iban") String iban, @Valid @RequestBody Account body) {
        String accept = request.getHeader("Accept");
        service.toggleAccountStatus(iban, body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
