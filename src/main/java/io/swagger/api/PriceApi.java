package io.swagger.api;

import io.swagger.model.Price;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Api(value = "price")
public interface PriceApi {
    @ApiOperation(value = "get a price by id", nickname = "getAPrice", notes = "Get price by Id ", response = Price.class, tags={ "Get price for pizza", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Price.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/price",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Price> getAPrice(@ApiParam(value = "pizza id", required = true) @RequestParam("id") String id);

}
