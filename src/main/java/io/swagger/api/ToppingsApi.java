package io.swagger.api;

import io.swagger.model.Toppings;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Api(value = "toppings")
public interface ToppingsApi {

    @ApiOperation(value = "Adds a topping item",
        nickname = "addTopping",
        notes = "Adds a topping to the system. If the topping name is duplicate, the old topping will be overridden with new value.",
        tags={ "Toppings Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Item created, or an existing item with the name already exists, override it"),
        @ApiResponse(code = 400, message = "Invalid input, object invalid") })
    @RequestMapping(value = "/toppings",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Toppings> addTopping(@ApiParam(value = "Topping item to add"  )  @Valid @RequestBody Toppings body);


    @ApiOperation(value = "Deletes a topping item", nickname = "deleteTopping", notes = "Delete an topping in the database", tags={ "Toppings Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "item deleted"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 404, message = "an existing item doesn't exist") })
    @RequestMapping(value = "/toppings",
        method = RequestMethod.DELETE)
    ResponseEntity<Toppings> deleteTopping(@NotNull @ApiParam(value = "Name of the topping that you want to delete.", required = true) @Valid @RequestParam(value = "name") String name);


    @ApiOperation(value = "searches topping",
        nickname = "searchTopping",
        notes = "By passing in the appropriate options, you can search for available toppings in the system. This API accepts two options: name and isPremium.\n" +
        "If the name is not null, this API will return the topping with that name. If the name is null but isPremium is present, this API will return toppings that match isPremium.\n"
        + "If all of the two parameters are none, this API will return all available toppings. Note that the name is unique for the toppings.",
        response = Toppings.class,
        responseContainer = "List",
        tags={ "Toppings Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "search results matching criteria", response = Toppings.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "items doesn't exist")})
    @RequestMapping(value = "/toppings",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Toppings>> searchTopping(@ApiParam(value = "Name for the searched topping. It is a unique value for all toppings. If the name is present, this API will return the topping with that name.") @Valid @RequestParam(value = "name", required = false) String name,
                                                 @ApiParam(value = "If the topping is premium. The API will return toppings that is premium or not. If name is present, the API will ignore this parameter.") @Valid @RequestParam(value = "searchPremium", required = false) Boolean searchPremium);


    @ApiOperation(value = "This API lists get the toppings image",
            nickname = "getToppingImage",
            notes = "The API returns the topping image based on the given image key (unique name of the topping)",
            tags={ "Toppings Operation", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return byte array for the image.") })
    @RequestMapping(value = "/toppings/image",
            produces = MediaType.IMAGE_JPEG_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<byte[]> getToppingImage(@ApiParam(required = true) @RequestParam(value = "name", required = true) String name);

}
