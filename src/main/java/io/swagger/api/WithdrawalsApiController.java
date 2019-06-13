package io.swagger.api;

import io.swagger.model.Withdrawal;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.WithdrawalsService;
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
public class WithdrawalsApiController implements WithdrawalsApi {

    private static final Logger log = LoggerFactory.getLogger(WithdrawalsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private WithdrawalsService service;

    @org.springframework.beans.factory.annotation.Autowired
    public WithdrawalsApiController(ObjectMapper objectMapper, HttpServletRequest request, WithdrawalsService service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<Withdrawal> createNewWithdrawal(@ApiParam(value = ""  )  @Valid @RequestBody Withdrawal body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Withdrawal>(service.createNewWithdrawal(body), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Withdrawal>> getAllWithdrawals() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Withdrawal>>((List<Withdrawal>) service.getAllWithdrawals(), HttpStatus.OK);
    }

    public ResponseEntity<List<Withdrawal>> getAllWithdrawalsConnectedToSpecifiedAccount(@ApiParam(value = "The IBAN",required=true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Withdrawal>>(service.getWithdrawalsByIban(iban), HttpStatus.OK);
    }

}
