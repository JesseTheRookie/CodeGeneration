/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

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
@Api(value = "login", description = "the login API")
public interface LoginApi {

    @ApiOperation(value = "Logs user into the system", nickname = "loginUser", notes = "", tags={ "login &amp; out", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "User logged in. The session ID is returned in a cookie named `JSESSIONID`. You need to include this cookie in subsequent requests. "),
        @ApiResponse(code = 400, message = "Invalid input, username and/or password is not correct"),
        @ApiResponse(code = 500, message = "Oops, something went wrong on the server. Sorry!") })
    @RequestMapping(value = "/login",
        consumes = { "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
    ResponseEntity<Void> loginUser(@ApiParam(value = "", required=true) @RequestParam(value="username", required=true)  String username,@ApiParam(value = "", required=true) @RequestParam(value="password", required=true)  String password);

}
