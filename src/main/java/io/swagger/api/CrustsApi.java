package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Crust;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(value = "crusts")
public interface CrustsApi {
    @ApiOperation(value = "Adds a crust item",
            nickname = "addCrust",
            notes = "Adds a crust to the system. If the crust name is duplicate, the old topping will be overridden with new value.",
            tags = {"Crust Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Item created, or an existing item already exists, override it"),
            @ApiResponse(code = 400, message = "Invalid input, object invalid")})
    @RequestMapping(value = "/crusts",
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Crust> addCrust(@ApiParam(value = "Crust item to add") @Valid @RequestBody Crust body);

    @ApiOperation(value = "Deletes a crust item", nickname = "deleteCrust", notes = "Delete a crust in the database", tags = {"Crust Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "item deleted"),
            @ApiResponse(code = 400, message = "invalid input, object invalid"),
            @ApiResponse(code = 404, message = "an existing item doesn't exist")})
    @RequestMapping(value = "/crusts",
            method = RequestMethod.DELETE)
    ResponseEntity<Crust> deleteCrust(@NotNull @ApiParam(value = "Name of the topping that you want to delete.", required = true) @Valid @RequestParam(value = "searchName") String searchName);

    @ApiOperation(value = "searches crust",
            nickname = "searchCrust",
            notes = "By passing in the appropriate options, you can search for available crusts in the system. This API accepts two options: name, isGlutenFree.\n" +
                    "If the name is not null, this API will return the topping with that name. If the name is null but isGlutenFree is present, this API will return crust that match isGlutenFree.\n"
                    + "If all of the two parameters are none, this API will return all available crust. Note that the name is unique for the crusts.",
            response = Crust.class,
            responseContainer = "List",
            tags = {"Crust Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "search results matching criteria", response = Crust.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "items doesn't exist")})
    @RequestMapping(value = "/crusts",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Crust>> searchCrust(@ApiParam(value = "Name for the searched crust. It is a unique value for all crusts. If the name is present, this API will return the crust with that name.") @Valid @RequestParam(value = "searchName", required = false) String searchName,
                                            @ApiParam(value = "If the crust is gluten free. The API will return crust that is gluten or not. If name is present, the API will ignore this parameter.") @Valid @RequestParam(value = "isGlutenFree", required = false) Boolean isGlutenFree);

    @ApiOperation(value = "This API lists get the toppings image",
            nickname = "getToppingImage",
            notes = "The API returns the topping image based on the given name",
            tags = {"Crust Operation",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return byte array for the image.")})
    @RequestMapping(value = "/crusts/image",
            produces = MediaType.IMAGE_JPEG_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<byte[]> getCrustImage(@RequestParam(value = "name") String name);
}
