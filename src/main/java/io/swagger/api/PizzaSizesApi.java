package io.swagger.api;

import io.swagger.model.PizzaSize;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<PizzaSize> addPizzaSizes(@ApiParam(value = ""  )  @Valid @RequestBody PizzaSize body);


    @ApiOperation(value = "Search pizza sizes. If name is not empty, it will search by pizza size name. Otherwise it returns all pizza sizes",
            nickname = "getPizzaSizes", notes = "Searches all available pizza sizes available in the store ", tags={ "PizzaSize Operation", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The pizza size options"),
        @ApiResponse(code = 404, message = "not found") })
    @RequestMapping(value = "/pizzaSizes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<PizzaSize>> getPizzaSizes(@ApiParam(value = "name of pizza size to return") @RequestParam(value = "name", required = false) String name);

    @ApiOperation(value = "Delete a pizza size by name", nickname = "deletePizzaSizeByName", notes = "Delete a pizza size", tags={ "PizzaSize Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "size deleted successfully"),
        @ApiResponse(code = 404, message = "bad request") })
    @RequestMapping(value = "/pizzaSizes",
        produces = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<PizzaSize> deletePizzaSizeByName(@ApiParam(value = "name of pizza size to delete", required = true)  @RequestParam("name") String name);

}
