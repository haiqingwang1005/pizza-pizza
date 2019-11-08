package io.swagger.service;

import io.swagger.models.Pizza;
import io.swagger.repository.PizzaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

  @Autowired @NotNull
  private PizzaRepository pizzaRepository;

  public Pizza addPizza(Pizza pizza) {
    UUID uuid = UUID.randomUUID();
    pizza.setId(uuid.toString());
    pizzaRepository.save(io.swagger.model.Pizza.convertToDaoModel(pizza));
    return pizza;
  }

  public Pizza getPizzaById(String id) {
    return io.swagger.model.Pizza.convertToApiModel(pizzaRepository.findOne(id));
  }



}