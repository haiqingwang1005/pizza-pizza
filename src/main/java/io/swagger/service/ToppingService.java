package io.swagger.service;

import io.swagger.repository.ToppingsRepository;
import io.swagger.model.ToppingType;
import io.swagger.model.Toppings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToppingService {
  private static final Logger log = LoggerFactory.getLogger(ToppingService.class);

  @NotNull
  private final ToppingsRepository toppingsRepository;

  @Autowired
  public ToppingService(
      ToppingsRepository toppingsRepository) {
    this.toppingsRepository = toppingsRepository;
  }

  public Toppings addTopping(Toppings topping) {
    String name = topping.getName();
    Boolean isPremium = topping.getIsPremium();
    ToppingType toppingType = topping.getToppingType();
    String description = topping.getDescription();

    if (name == null || name.trim().equals("")) {
      return null;
    }

    Toppings existingTopping = toppingsRepository.findByName(name);
    if (existingTopping != null) {
      log.info(String.format("PizzaPizza Toppings name %s already exists! override it!", name));
      existingTopping.setIsPremium(isPremium);
      existingTopping.setToppingType(toppingType);
      existingTopping.setDescription(description);
      toppingsRepository.save(existingTopping);
      return existingTopping;
    }

    Toppings newTopping = Toppings.builder()
        .name(name)
        .isPremium(isPremium)
        .toppingType(toppingType)
        .description(description)
        .build();
    toppingsRepository.insert(newTopping);

    log.info(
        String.format("Topping name: %s, premium: %b, toppingType: %s, description: %s",
            name, isPremium, toppingType.toString(), description));
    return newTopping;
  }

  public List<Toppings> findToppings(String toppingName, Boolean isPremium) {
    List<Toppings> toppings = new ArrayList<>();
    if (toppingName != null) {
      Toppings result = toppingsRepository.findByName(toppingName);
      if (result != null) {
        toppings.add(result);
      }
    } else {
      Set<Toppings> set = new HashSet<>(toppingsRepository.findAll());
      if (isPremium != null) {
        set.retainAll(toppingsRepository.findByIsPremium(isPremium));
      }
      toppings.addAll(set);
    }
    return toppings;
  }

  public Toppings deleteToppings(String searchName) {
    if (searchName == null) {
      return null;
    }
    Toppings exitingToppings = toppingsRepository.findByName(searchName);
    toppingsRepository.delete(exitingToppings);
    return exitingToppings;
  }
}
