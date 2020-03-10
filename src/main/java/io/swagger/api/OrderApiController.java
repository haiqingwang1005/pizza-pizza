package io.swagger.api;

import io.swagger.model.Order;
import io.swagger.service.OrderService;
import io.swagger.service.PizzaService;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApiController implements OrderApi{

  private final OrderService orderService;
  private final PizzaService pizzaService;

  @Autowired
  public OrderApiController(OrderService orderService, PizzaService pizzaService) {
    this.orderService = orderService;
    this.pizzaService = pizzaService;
  }

  @Override
  public ResponseEntity<List<Order>> getOrder(@ApiParam(value = "order id",required = false) @RequestParam("id") String id,
                                              @ApiParam(value = "user name",required = false) @RequestParam("name") String name) {
    List<Order> list = new ArrayList<>();
    if (!StringUtils.isEmpty(id)) {
      list = new ArrayList<>();
      list.add(orderService.getOrderById(id));
    }
    else if (!StringUtils.isEmpty(name)) {
      list = orderService.getOrderByCustomerName(name);
    }

    if (CollectionUtils.isEmpty(list)) {
      return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Order> makeOrder(@ApiParam(value = "Order to add" )  @Valid @RequestBody Order order) {
    if (isOrderValid(order)) {
      Order result = orderService.addOrder(order);
      return new ResponseEntity<>(result, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  private boolean isOrderValid(Order order) {
    if (StringUtils.isEmpty(order.getName())) {
      return false;
    }
    List<String> pizzaList = order.getPizzaIds();
    if (CollectionUtils.isEmpty(pizzaList)) {
      return false;
    }
    for (String pizzaId: pizzaList) {
      if (pizzaService.getPizzaById(pizzaId) == null) {
        return false;
      }
    }
    return true;
  }
}