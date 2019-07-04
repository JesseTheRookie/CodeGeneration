/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Transaction;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-07-04T23:40:18.106Z[GMT]")
@Api(value = "transactions", description = "the transactions API")
public interface TransactionsApi {

    @ApiOperation(value = "Create a new transaction", nickname = "createTransaction", notes = "Creates a transaction of the specified type, ammount and accounts", response = Integer.class, authorizations = {
        @Authorization(value = "cookieAuth")    }, tags={ "transaction", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Transaction created", response = Integer.class),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 403, message = "Forbidden, you do not have the required rights"),
        @ApiResponse(code = 500, message = "Oops, something went wrong on the server. Sorry!") })
    @RequestMapping(value = "/transactions",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Integer> createTransaction(@ApiParam(value = ""  )  @Valid @RequestBody Transaction body);


    @ApiOperation(value = "Returns the specified transaction", nickname = "getTransactionById", notes = "Returns the specified transaction", response = Transaction.class, responseContainer = "List", authorizations = {
        @Authorization(value = "cookieAuth")    }, tags={ "transaction", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Transaction found", response = Transaction.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 403, message = "Forbidden, you do not have the required rights"),
        @ApiResponse(code = 500, message = "Oops, something went wrong on the server. Sorry!") })
    @RequestMapping(value = "/transactions/{transactionId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> getTransactionById(@ApiParam(value = "The transaction id",required=true) @PathVariable("transactionId") Integer transactionId);


    @ApiOperation(value = "Get transactions depending on the optional parameters; the abstinence of any parameters results in all transactions.", nickname = "getTransactions", notes = "", response = Transaction.class, responseContainer = "List", authorizations = {
        @Authorization(value = "cookieAuth")    }, tags={ "transaction", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Transaction(s) found", response = Transaction.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 403, message = "Forbidden, you do not have the required rights"),
        @ApiResponse(code = 500, message = "Oops, something went wrong on the server. Sorry!") })
    @RequestMapping(value = "/transactions",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> getTransactions(@ApiParam(value = "The iban of the sending backaccount") @Valid @RequestParam(value = "fromIban", required = false) String fromIban,@ApiParam(value = "The iban of the receiving backaccount") @Valid @RequestParam(value = "toIban", required = false) String toIban,@ApiParam(value = "The type of transaction", allowableValues = "transaction, deposit, withdrawal") @Valid @RequestParam(value = "Type", required = false) String type,@ApiParam(value = "The id of the user who performed the transaction") @Valid @RequestParam(value = "performedBy", required = false) Integer performedBy);


    @ApiOperation(value = "Returns all transaction with the specified iban in the fromIban or toIban field", nickname = "getTransactionsByIban", notes = "Returns all transaction which involve this iban (from or to)", response = Transaction.class, responseContainer = "List", authorizations = {
        @Authorization(value = "cookieAuth")    }, tags={ "transaction", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Transaction(s) found", response = Transaction.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 403, message = "Forbidden, you do not have the required rights"),
        @ApiResponse(code = 500, message = "Oops, something went wrong on the server. Sorry!") })
    @RequestMapping(value = "/transactions/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> getTransactionsByIban(@ApiParam(value = "The iban for fromIban AND toIban",required=true) @PathVariable("iban") String iban);

}
