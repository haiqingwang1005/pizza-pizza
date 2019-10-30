package io.swagger.service;

import io.swagger.models.Pizza;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

  // TODO replace with real JAP-MongoDB
  private List<Pizza> store = new ArrayList<>();

  public Pizza addPizza(Pizza pizza) {
    pizza.setId(store.size()+"");
    store.add(pizza);
    return pizza;
  }

  public Pizza getPizzaById(String id) {
    int index = Integer.valueOf(id);
    return store.get(index);
  }



}
