package io.swagger.api;

import io.swagger.io.OrderRequestBody;
import io.swagger.model.Order;
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
@Api(value = "order")
public interface OrderApi {

    @ApiOperation(value = "get a order by order id or customer name. If order id is not empty, it searches by order id. " +
            "If order id is empty and customer name is not empty, it searches by user name",
            nickname = "getOrder", notes = "Get order by Id or name", response = Order.class, tags={ "Order Operation", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Order.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "order not found") })
    @RequestMapping(value = "/order",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Order>> getOrder(@ApiParam(value = "Order id. If the id is empty, it will search by name") @RequestParam(value = "id", required = false) String id,
                                         @ApiParam(value = "Customer name") @RequestParam(value = "name", required = false) String name);

    @ApiOperation(value = "Get all orders",
        nickname = "getAllOrder", notes = "Get all order", response = Order.class, tags={ "Admin Order Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "search results matching criteria", response = Order.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "order not found") })
    @RequestMapping(value = "/admin/order",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Order>> getAllOrder();


    @ApiOperation(value = "make an order", nickname = "makeOrder", notes = "make an order to the system", response = Order.class, tags={ "Order Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "item created", response = Order.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid"), })
    @RequestMapping(value = "/order",
        produces = { "application/json" },
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Order> makeOrder(@ApiParam(value = "Order to add") @Valid @RequestBody OrderRequestBody orderRequest);

}
