package io.swagger.api;

import io.swagger.model.Deposit;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-11T12:03:46.065Z[GMT]")
@Controller
public class DepositsApiController implements DepositsApi {

    private static final Logger log = LoggerFactory.getLogger(DepositsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public DepositsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createDeposit(@ApiParam(value = "Account details" ,required=true )  @Valid @RequestBody Deposit body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Deposit>> getDeposits() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Deposit>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Deposit>> getDepositsByIban(@DecimalMin("1")@ApiParam(value = "The account IBAN",required=true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Deposit>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
