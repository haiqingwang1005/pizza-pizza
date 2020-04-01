package io.swagger.service;

import io.swagger.repository.PizzaRepository;
import io.swagger.repository.PriceRuleRepository;
import io.swagger.model.Pizza;
import io.swagger.model.Price;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

  @NotNull
  private final PriceRuleRepository priceRuleRepository;

  @NotNull
  private final PizzaRepository pizzaRepository;

  @Autowired
  public PriceService(
      PriceRuleRepository priceRuleRepository, PizzaRepository pizzaRepository) {
    this.priceRuleRepository = priceRuleRepository;
    this.pizzaRepository = pizzaRepository;
  }

  public Price getPrice(String pizzaId) {
    Pizza pizza = pizzaRepository.findOne(pizzaId);
    if (pizza == null) {
      return null;
    }
    return Price.builder().price(pizza.getPrice()).build();
  }
}
