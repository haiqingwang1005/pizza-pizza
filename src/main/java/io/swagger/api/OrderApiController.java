package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Order;
import io.swagger.service.OrderService;
import io.swagger.service.PizzaService;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
  public ResponseEntity<Order> getOrderById(@ApiParam(value = "order id",required=true) @PathVariable("id") String id) {
    Order order = orderService.getOrderById(id);
    if (order == null) {
      return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Order>(order, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Order>> getOrderByName(String name) {
    List<Order> list = orderService.getOrderByCustomerName(name);
    if (CollectionUtils.isEmpty(list)) {
      return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Order> makeOrder(@ApiParam(value = "Order to add"  )  @Valid @RequestBody Order order) {
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