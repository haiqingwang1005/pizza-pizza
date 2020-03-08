package io.swagger.service;

import io.swagger.model.Pizza;
import io.swagger.model.PriceRule;
import io.swagger.model.Price;
import io.swagger.model.Toppings;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.PriceRuleRepository;
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
    PriceRule priceRule = priceRuleRepository.findOne(PriceRule.DEFAULT_PRICE_RULE_ID);
    double result = 0;

    // check base
    switch (pizza.getBase().getPizzaSize().getTag()) {
      case "small":
        result += priceRule.getSmallBasePrice();
        break;
      case "regular":
        result += priceRule.getMediumBasePrice();
        break;
      case "large":
        result += priceRule.getLargeBasePrice();
        break;
      default:
        throw new UnsupportedOperationException("Unknown pizza tag " + pizza.getBase().getPizzaSize().getTag());
    }

    for(Toppings toppings : pizza.getToppings()){
      switch (toppings.getToppingType()) {
        case MEAT:
          result += priceRule.getMeatPrice();
          break;
        case VEGETABLE:
          result += priceRule.getVegetablePrice();
          break;
         default:
           throw new UnsupportedOperationException("Unknown pizza topping type " + toppings.getToppingType().name());
      }
    }

    return Price.builder().price(result).build();
  }
}
