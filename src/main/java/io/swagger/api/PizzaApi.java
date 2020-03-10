package io.swagger.api;

import io.swagger.model.Pizza;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Api(value = "pizza")
public interface PizzaApi {

    @ApiOperation(value = "adds pizza", nickname = "addPizza", notes = "Adds pizza to the system", response = Pizza.class, tags={ "Pizza Operation", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "item created", response = Pizza.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "an existing item already exists") })
    @RequestMapping(value = "/pizza",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Pizza> addPizza(@ApiParam(value = "pizza to be added") @Valid @RequestBody Pizza pizza);


    @ApiOperation(value = "get a pizza by id or pizza name. If pizza id is not empty, it searches by pizza id. " +
            "If pizza is empty and pizza name is not empty, it searches by name. If both are empty, it returns all pizza",
            nickname = "getPizzaById", notes = "Get pizza by Id ", response = Pizza.class, tags={ "Pizza Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "search results matching criteria", response = Pizza.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/pizza",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Pizza>> getPizza(@ApiParam(value = "pizza id", required = false) @RequestParam("id") String id,
                                         @ApiParam(value = "pizza display name", required = false) @RequestParam("name") String name);
}
