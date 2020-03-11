package io.swagger.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CheckoutApi {
    @ApiOperation(value = "",
            nickname = "checkout", notes = "Get order by Id or name", response = Order.class, tags={ "Order Operation", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "search results matching criteria", response = Order.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "order not found") })
    @RequestMapping(value = "/order",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Order>> checkout(@ApiParam(value = "Order id. If the id is empty, it will search by name") @RequestParam(value = "id", required = false) String id,
                                         @ApiParam(value = "Customer name") @RequestParam(value = "name", required = false) String name);

}
