package io.swagger.service;

import io.swagger.models.Order;
import io.swagger.models.Pizza;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  // TODO replace with real JAP-MongoDB
  private List<Order> store = new ArrayList<>();

  public Order addOrder(Order order) {
    order.setId(store.size()+"");
    store.add(order);
    return order;
  }

  public Order getOrderById(String id) {
    int index = Integer.valueOf(id);
    return store.get(index);
  }
}
