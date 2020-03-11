package io.swagger.api;

import io.swagger.io.OrderRequestBody;
import io.swagger.model.Order;
import io.swagger.model.Promotion;
import io.swagger.service.OrderService;
import io.swagger.service.PizzaService;
import io.swagger.annotations.ApiParam;

import io.swagger.service.PromotionService;
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
public class OrderApiController implements OrderApi {

  private final OrderService orderService;

  @Autowired
  public OrderApiController(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public ResponseEntity<List<Order>> getOrder(
      @RequestParam(value = "id", required = false) String id,
      @RequestParam(value = "name", required = false) String name) {
    List<Order> list = new ArrayList<>();
    if (!StringUtils.isEmpty(id)) {
      list = new ArrayList<>();
      list.add(orderService.getOrderById(id));
    } else if (!StringUtils.isEmpty(name)) {
      list = orderService.getOrderByCustomerName(name);
    }

    if (CollectionUtils.isEmpty(list)) {
      return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Order>> getAllOrder() {
    List<Order> list = orderService.getAllOrders();
    if (CollectionUtils.isEmpty(list)) {
      return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Order> makeOrder(
      @ApiParam(value = "Order details to add") @Valid @RequestBody OrderRequestBody body) {
    Order order = orderService.addOrder(
        body.getPizzaIds(),
        body.getUsername(),
        body.getPromotionCode());
    if (order != null) {
      return new ResponseEntity<>(order, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }


}