package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.models.Order;
import io.swagger.service.OrderService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pizza/v1")
@RestController
public class OrderApiController implements io.swagger.apis.OrderApi{

  @Autowired private OrderService orderService;

  @Override
  public ResponseEntity<Order> getAOrder(@ApiParam(value = "order id",required=true) @PathVariable("id") String id) {
    return new ResponseEntity<Order>(orderService.getOrderById(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Order> makeOrder(@ApiParam(value = "Order to add"  )  @Valid @RequestBody Order order) {
    Order result = orderService.addOrder(order);
    return new ResponseEntity<Order>(result, HttpStatus.OK);
  }
}