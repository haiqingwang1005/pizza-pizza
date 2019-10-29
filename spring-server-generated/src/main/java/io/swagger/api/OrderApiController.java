package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.models.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public class OrderApiController implements io.swagger.apis.OrderApi{

  @Override
  public ResponseEntity<Order> getAOrder(@ApiParam(value = "order id",required=true) @PathVariable("id") String id) {
    return null;
  }
}
