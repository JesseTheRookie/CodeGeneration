/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Withdrawal;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-29T12:43:24.827Z[GMT]")
@Api(value = "Withdrawals", description = "the Withdrawals API")
public interface WithdrawalsApi {

    @ApiOperation(value = "Create a new Withdrawal", nickname = "createNewWithdrawal", notes = "Creates a new Withdrawal", response = Withdrawal.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "withdrawal", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Withdrawal successfully created!", response = Withdrawal.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/withdrawals",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Withdrawal> createNewWithdrawal(@ApiParam(value = ""  )  @Valid @RequestBody Withdrawal body);


    @ApiOperation(value = "gets all Withdrawals", nickname = "getAllWithdrawals", notes = "Gets a list with all Withdrawal transactions", response = Withdrawal.class, responseContainer = "List", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "withdrawal", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The list with all Withdrawals is successfully fetched", response = Withdrawal.class, responseContainer = "List"),
        @ApiResponse(code = 200, message = "Oops, it looks like it didn't do what it was supposed to be doing..") })
    @RequestMapping(value = "/withdrawals",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Withdrawal>> getAllWithdrawals();


    @ApiOperation(value = "Get all withdrawals for a specific account", nickname = "getAllWithdrawalsConnectedToSpecifiedAccount", notes = "Gets a list of all withdraw transactions of the specified account", response = Withdrawal.class, responseContainer = "List", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "withdrawal", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The list with all Withdrawals for the specified account is successfully fetched", response = Withdrawal.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/withdrawals/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Withdrawal>> getAllWithdrawalsConnectedToSpecifiedAccount(@ApiParam(value = "The IBAN",required=true) @PathVariable("iban") String iban);

}
