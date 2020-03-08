package io.swagger.service;

import io.swagger.model.Pizza;
import io.swagger.repository.PizzaRepository;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

  @NotNull
  private final PizzaRepository pizzaRepository;

  @Autowired
  public PizzaService(PizzaRepository pizzaRepository) {
    this.pizzaRepository = pizzaRepository;
  }

  public Pizza addPizza(Pizza pizza) {
    pizzaRepository.save(pizza);
    return pizza;
  }

  public Pizza getPizzaById(String id) {
    return pizzaRepository.findOne(id);
  }

  public Pizza getPizzaByName(String name) {
    return pizzaRepository.findByDisplayName(name);
  }

  public List<Pizza> getAllPizzas() {
    return pizzaRepository.findAll();
  }
}