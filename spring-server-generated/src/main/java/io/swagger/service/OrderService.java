package io.swagger.service;

import io.swagger.models.Order;
import io.swagger.repository.OrderRepository;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired @NotNull private OrderRepository orderRepository;

  public Order addOrder(Order order) {
    UUID uuid = UUID.randomUUID();
    order.setId(uuid.toString());
    orderRepository.save(io.swagger.model.Order.convertToDaoModel(order));
    return order;
  }

  public Order getOrderById(String id) {
    return io.swagger.model.Order.convertToApiModel(orderRepository.findOne(id));
  }
}