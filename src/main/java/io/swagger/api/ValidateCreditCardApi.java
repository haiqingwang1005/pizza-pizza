package io.swagger.api;

import io.swagger.model.CreditCard;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(value = "validateCreditCard")
public interface ValidateCreditCardApi {

    @ApiOperation(value = "", nickname = "isValid", notes = "Return whether the given credit card is accepted at the pizza store", tags={ "Validate Credit Card", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returned successfully"),
        @ApiResponse(code = 400, message = "Bad input") })
    @RequestMapping(value = "/validateCreditCard",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Boolean> isValid(@ApiParam(value = "the credit card", required=true) @Valid @RequestBody CreditCard creditCard);
}
