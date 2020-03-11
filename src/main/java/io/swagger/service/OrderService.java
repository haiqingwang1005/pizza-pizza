package io.swagger.service;

import io.swagger.model.Price;
import io.swagger.model.Promotion;
import io.swagger.repository.OrderRepository;
import io.swagger.model.Order;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class OrderService {
  private static final Logger log = LoggerFactory.getLogger(OrderService.class);

  private final OrderRepository orderRepository;
  private final PriceService priceService;
  private final PromotionService promotionService;
  private final PizzaService pizzaService;

  @Autowired
  public OrderService(
      OrderRepository orderRepository,
      PriceService priceService,
      PromotionService promotionService,
      PizzaService pizzaService) {
    this.orderRepository = orderRepository;
    this.priceService = priceService;
    this.promotionService = promotionService;
    this.pizzaService = pizzaService;
  }

  public Order addOrder(List<String> pizzaIds, String username, String code) {
    if (StringUtils.isEmpty(username)) {
      log.error("username is empty");
      return null;
    }

    if (CollectionUtils.isEmpty(pizzaIds)) {
      log.error("no pizza id");
      return null;
    }

    Promotion promotion = promotionService.getPromotionFromCode(code);
    if (promotion == null) {
      log.error("promotion is invalid");
      return null;
    }

    double price = 0;
    for (String pizzaId: pizzaIds) {
      Price priceObj = priceService.getPrice(pizzaId);
      if (priceObj == null) {
        log.error("Not price for the pizza " + pizzaId);
        return null;
      }
      price += priceObj.getPrice();
    }

    Order order = Order.builder()
        .discount(promotion.getDiscount())
        .username(username)
        .pizzaIds(pizzaIds)
        .price(price)
        .build();
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