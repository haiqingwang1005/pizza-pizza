package io.swagger.api;

import io.swagger.io.RegisterRequestBody;
import io.swagger.io.UserDetailsResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(value = "register")
public interface RegisterApi {
    @ApiOperation(value = "Register a new account",
            nickname = "registerAccount",
            notes = "Register a new account to the system. If the account username duplicates, It will return error.",
            tags={ "Account Registration", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully register an account"),
            @ApiResponse(code = 400, message = "Invalid input, missing necessary parameters or invalid account type"),
            @ApiResponse(code = 409, message = "Account already exists") }
        )
    @RequestMapping(value = "/register",
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UserDetailsResponse> registerAccount(@ApiParam(value = "Account to be registered"  )  @Valid @RequestBody RegisterRequestBody body);
}
