package io.swagger.service;

import io.swagger.model.Pizza;
import io.swagger.model.PriceRule;
import io.swagger.models.Price;
import io.swagger.model.Toppings;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.PriceRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

  @Autowired
  private PriceRuleRepository priceRuleRepository;

  @Autowired
  private PizzaRepository pizzaRepository;

  public Price getPrice(String pizzaId) {
    Pizza pizza = pizzaRepository.findOne(pizzaId);
    PriceRule priceRule = priceRuleRepository.findOne(PriceRule.DEFAULT_PRICE_RULE_ID);
    double result = 0;

    // check base
    switch (pizza.getBase().getPizzaSize().getDescription()) {
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
        throw new UnsupportedOperationException("Unkown pizza size " + pizza.getBase().getPizzaSize().getDescription());
    }

    for(Toppings toppings : pizza.getToppings()){
      switch (toppings.getToppingType()) {
        case MEAT:
          result += priceRule.getMeatPrice();
          break;
        case VEGETABLE:
          result += priceRule.getVagetablePrice();
          break;
         default:
           throw new UnsupportedOperationException("Unkown pizza topping type " + toppings.getToppingType().name());
      }
    }

    Price price = new Price();
    price.setPrice(result);
    return price;
  }
}
