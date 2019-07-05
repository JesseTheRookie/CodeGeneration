package io.swagger.api;

import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-29T12:43:24.827Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;
    private final TransactionService transactionService;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request, TransactionService transactionService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.transactionService = transactionService;
    }

    public ResponseEntity<Integer> createTransaction(@ApiParam(value = "") @Valid @RequestBody Transaction body) throws ApiException {
        String accept = request.getHeader("Accept");
        transactionService.initiateTransaction(body);
        return new ResponseEntity<Integer>(Integer.valueOf(body.getId()), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Transaction>> getTransactions(@ApiParam(value = "The iban of the sending backaccount") @Valid @RequestParam(value = "fromIban", required = false) String fromIban,
                                                             @ApiParam(value = "The iban of the receiving backaccount") @Valid @RequestParam(value = "toIban", required = false) String toIban,
                                                             @ApiParam(value = "The type of the transaction") @Valid @RequestParam(value = "type", required = false) Transaction.TransactionType type,
                                                             @ApiParam(value = "The userId of the user who performed the transaction") @Valid @RequestParam(value = "performedBy", required = false) Integer performedBy) throws ApiException{
        String accept = request.getHeader("Accept");
        if (fromIban != null) {
            return new ResponseEntity<List<Transaction>>((List<Transaction>) transactionService.getTransactionsByFromIban(fromIban), HttpStatus.OK); // deze werkt
        } else if (toIban != null) {
            return new ResponseEntity<List<Transaction>>((List<Transaction>) transactionService.getTransactionsByToIban(toIban), HttpStatus.OK); // deze dan weer niet.. Welles
        } else if (performedBy != null) {
            return new ResponseEntity<List<Transaction>>((List<Transaction>) transactionService.getTransactionsByPerformedBy(performedBy), HttpStatus.OK);
        } else if (type != null){
            return new ResponseEntity<List<Transaction>>((List<Transaction>) transactionService.getTransactionsByType(type), HttpStatus.OK);
        }else{
            return new ResponseEntity<List<Transaction>>((List<Transaction>) transactionService.getAllTransactions(), HttpStatus.OK);
        }
    }

    public ResponseEntity<Transaction> getTransactionById(@Min(1)@ApiParam(value = "The transaction ID",required=true, allowableValues = "") @PathVariable("transactionId") Integer transactionId) throws ApiException {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Transaction>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
    }
    public ResponseEntity<List<Transaction>> getTransactionsByIban(@Min(1)@ApiParam(value = "The transaction ID",required=true, allowableValues = "") @PathVariable("iban") String iban) throws ApiException {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Transaction>>((List<Transaction>) transactionService.getTransactionsByIban(iban), HttpStatus.OK);
    }
}
