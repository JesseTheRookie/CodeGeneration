package io.swagger.api;

import com.google.common.collect.Lists;
import io.swagger.model.Account;
import io.swagger.model.User;
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
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private SwaggerService service;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, SwaggerService service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    // Werkt
    public ResponseEntity<Integer> createUser(@ApiParam(value = ""  )  @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        service.createUser(body);
        return new ResponseEntity<Integer>(Integer.valueOf(body.getId()),HttpStatus.CREATED);
    }

    // Werkt
    public ResponseEntity<List<Account>> getAccountsByUserId(@ApiParam(value = "The user Id",required=true) @PathVariable("userId") Integer userId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Account>>(service.getAccountsByUserId(userId), HttpStatus.OK);
    }

    // Werkt
    public ResponseEntity<User> getUserById(@Min(1)@ApiParam(value = "The user ID",required=true, allowableValues = "") @PathVariable("userId") Integer userId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<User>(service.getUserById(userId), HttpStatus.OK);
    }

    // Werkt
    public ResponseEntity<List<User>> getUsers() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<User>>((List<User>)service.getAllUsers(), HttpStatus.OK);
    }

    public ResponseEntity<String> loginUser(@ApiParam(value = "Username and password" ,required=true )  @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> logoutUser() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
