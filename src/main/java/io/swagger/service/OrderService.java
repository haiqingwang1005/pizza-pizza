package io.swagger.service;

import io.swagger.repository.OrderRepository;
import io.swagger.model.Order;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @NotNull
  private final OrderRepository orderRepository;

  @Autowired
  public OrderService(
      OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order addOrder(Order order) {
    orderRepository.save(order);
    return order;
  }

  public Order getOrderById(String id) {
    return orderRepository.findOne(id);
  }

  public List<Order> getOrderByCustomerName(String name) {
    return orderRepository.findByName(name);
  }

  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }
}