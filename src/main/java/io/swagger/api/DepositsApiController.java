package io.swagger.api;

import io.swagger.model.Deposit;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.DepositsService;
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
public class DepositsApiController implements DepositsApi {

    private static final Logger log = LoggerFactory.getLogger(DepositsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private DepositsService service;

    @org.springframework.beans.factory.annotation.Autowired
    public DepositsApiController(ObjectMapper objectMapper, HttpServletRequest request, DepositsService service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    //werkt
    public ResponseEntity<Void> createDeposit(@ApiParam(value = "Account details", required = true) @Valid @RequestBody Deposit body) {
        String accept = request.getHeader("Accept");
        service.createDeposit(body);
        return new ResponseEntity<Void>((HttpStatus.CREATED));
    }

    //werkt
    public ResponseEntity<List<Deposit>> getDeposits() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Deposit>>((List<Deposit>) service.getAllDeposits(), HttpStatus.OK);
    }

    //werkt
    /**
     * geeft alleen deposits terug als het IBANnr een account is in ons systeem
     */
    public ResponseEntity<List<Deposit>> getDepositsByIban(@DecimalMin("1") @ApiParam(value = "The account IBAN", required = true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Deposit>>(service.getDepositByIban(iban), HttpStatus.OK);
    }
}
