package io.swagger.api;

import io.swagger.model.StoreLocation;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "Stores Location")
public interface StoresApi {

    @ApiOperation(value = "This API lists all three of our stores.",
        nickname = "listStores",
        notes = "The API returns both the street address and the latitude/longitude of all the three Pizza stores.",
        response = StoreLocation.class, responseContainer = "List", tags={ "Stores Location", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "All available pizza stores, which have street and geo location information.", response = StoreLocation.class, responseContainer = "List") })
    @RequestMapping(value = "/stores",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<StoreLocation>> listStores();

}
