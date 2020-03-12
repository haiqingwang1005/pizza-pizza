package io.swagger.api;

import io.swagger.io.OrderRequestBody;
import io.swagger.model.Order;
import io.swagger.service.OrderService;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApiController implements OrderApi {
  private static final Logger log = LoggerFactory.getLogger(OrderApiController.class);
  private final OrderService orderService;

  @Autowired
  public OrderApiController(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public ResponseEntity<List<Order>> getOrderByName(@RequestParam(value = "name", required = true) String name) {
    String username = getUsernameFromAuth();
    if (!StringUtils.equals(username, name)) {
      log.error("username mismatch");
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    List<Order> list = orderService.getOrderByCustomerName(name);

    if (CollectionUtils.isEmpty(list)) {
      return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Order>> getOrder(@RequestParam(value = "id", required = false) String id) {
    List<Order> list = new ArrayList<>();
    if (!StringUtils.isEmpty(id)) {
      list.add(orderService.getOrderById(id));
    } else {
      list = orderService.getAllOrders();
    }

    if (CollectionUtils.isEmpty(list)) {
      return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Order> makeOrder(
      @ApiParam(value = "Order details to add") @Valid @RequestBody OrderRequestBody body) {
    String username = getUsernameFromAuth();
    if (StringUtils.isEmpty(username)) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    Order order = orderService.addOrder(
        body.getPizzaIds(),
        username,
        body.getPromotionCode());
    if (order != null) {
      return new ResponseEntity<>(order, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  private String getUsernameFromAuth() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    final String username = authentication.getPrincipal().toString();
    return username;
  }
}