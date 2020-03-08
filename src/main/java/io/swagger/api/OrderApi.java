package io.swagger.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Order;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
@Api(value = "order")
public interface OrderApi {

    @ApiOperation(value = "get a order by order id", nickname = "getOrderById", notes = "Get order by Id ", response = Order.class, tags={ "Order Operation", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Order.class),
        @ApiResponse(code = 404, message = "order not found") })
    @RequestMapping(value = "/order/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Order> getOrderById(@ApiParam(value = "order id", required = true) @PathVariable("id") String id);

    @ApiOperation(value = "get a order by customer name", nickname = "getOrderByName", notes = "Get order by customer name ", response = Order.class, tags={ "Order Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "search results matching criteria", response = Order.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "order not found") })
    @RequestMapping(value = "/order/{name}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Order>> getOrderByName(@ApiParam(value = "customer name", required = true) @PathVariable("name") String name);

    @ApiOperation(value = "make an order", nickname = "makeOrder", notes = "make an order to the system", response = Order.class, tags={ "Order Operation", })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "item created", response = Order.class),
        @ApiResponse(code = 400, message = "invalid input, object invalid"), })
    @RequestMapping(value = "/order",
        produces = { "application/json" },
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Order> makeOrder(@ApiParam(value = "Order to add") @Valid @RequestBody Order order);

}
