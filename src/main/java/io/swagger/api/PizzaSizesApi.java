package io.swagger.api;

import io.swagger.model.PizzaSize;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Api(value = "pizzaSizes")
public interface PizzaSizesApi {

    @ApiOperation(value = "Add a pizza size", nickname = "addPizzaSize", notes = "Adds a pizza size into the system", tags={ "PizzaSize Operation", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "size created successfully"),
        @ApiResponse(code = 405, message = "invalid input") })
    @RequestMapping(value = "/pizzaSizes",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<PizzaSize> addPizzaSize(@ApiParam(value = ""  )  @Valid @RequestBody PizzaSize body);


    @ApiOperation(value = "Search all pizza sizes", nickname = "getPizzaSizes", notes = "Searches all available pizza sizes available in the store ", tags={ "PizzaSize Operation", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The pizza size options"),
        @ApiResponse(code = 404, message = "not found") })
    @RequestMapping(value = "/pizzaSizes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<PizzaSize>> getPizzaSizes();


    @ApiOperation(value = "Get a specific pizza size by size tag", nickname = "getSizeByTag", notes = "", response = PizzaSize.class, tags={ "PizzaSize Operation", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successfully returned", response = PizzaSize.class),
        @ApiResponse(code = 404, message = "not found") })
    @RequestMapping(value = "/pizzaSizes/{tag}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PizzaSize> getSizeByTag(@ApiParam(value = "tag of pizza size to return",required=true) @PathVariable("tag") String tag);


    @ApiOperation(value = "Delete a pizza size by tag", nickname = "deletePizzaSizeByTag", notes = "Delete a pizza size", tags={ "PizzaSize Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "size deleted successfully"),
        @ApiResponse(code = 404, message = "bad request") })
    @RequestMapping(value = "/pizzaSizes/{tag}",
        produces = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<PizzaSize> deletePizzaSizeByTag(@ApiParam(value = "tag of pizza size to delete", required=true)  @PathVariable("tag") String tag);

}
