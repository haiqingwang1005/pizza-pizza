package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Pizza;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
@Api(value = "pizza")
public interface PizzaApi {

    @ApiOperation(value = "adds pizza", nickname = "addPizza", notes = "Adds pizza to the system", response = Pizza.class, tags={ "Pizza Operation", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "item created", response = Pizza.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "an existing item already exists") })
    @RequestMapping(value = "/pizza",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Pizza> addPizza(@ApiParam(value = "pizza to be added") @Valid @RequestBody Pizza pizza);


    @ApiOperation(value = "get a pizza by id", nickname = "getPizzaById", notes = "Get pizza by Id ", response = Pizza.class, tags={ "Pizza Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "search results matching criteria", response = Pizza.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/pizza/{id}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Pizza> getPizzaById(@ApiParam(value = "pizza id", required = true) @PathVariable("id") String id);

    @ApiOperation(value = "get a pizza by display name", nickname = "getPizzaByName", notes = "Get pizza by displayName ", response = Pizza.class, tags={ "Pizza Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "search results matching criteria", response = Pizza.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/pizza/{name}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Pizza> getPizzaByName(@ApiParam(value = "pizza display name", required = true) @PathVariable("name") String name);

    @ApiOperation(value = "get all pizzas", nickname = "getPizza", notes = "Get all pizzas", response = Pizza.class, responseContainer = "List", tags={ "Pizza Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "search results matching criteria", response = Pizza.class)})
    @RequestMapping(value = "/pizza",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Pizza>> getAllPizza();

}
