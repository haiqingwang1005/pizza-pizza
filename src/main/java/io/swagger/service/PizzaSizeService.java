package io.swagger.service;

import io.swagger.model.PizzaSize;
import io.swagger.repository.PizzaSizesRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PizzaSizeService {
  private static final Logger log = LoggerFactory.getLogger(PizzaSizeService.class);
  private final PizzaSizesRepository pizzaSizesRepository;

  @Autowired
  public PizzaSizeService(PizzaSizesRepository pizzaSizesRepository) {
    this.pizzaSizesRepository = pizzaSizesRepository;
  }

  public PizzaSize addPizzaSize(PizzaSize pizzaSize) {
    String newTag = pizzaSize.getTag();
    Long newInch = pizzaSize.getInch();
    Integer newSlices = pizzaSize.getNumberOfSlices();
    Integer newCaloriesPerSlice = pizzaSize.getCaloriesPerSlice();

    if (newTag == null) {
      return null;
    }

    PizzaSize existingPizzaSize = pizzaSizesRepository.findByTag(newTag);
    if (existingPizzaSize != null) {
      log.info(String.format("Pizza size with tag %d already exists, modify it.", newTag));
      if (newCaloriesPerSlice != null) {
        existingPizzaSize.setCaloriesPerSlice(newCaloriesPerSlice);
      }
      if (newInch != null) {
        existingPizzaSize.setInch(newInch);
      }
      if (newSlices != null) {
        existingPizzaSize.setNumberOfSlices(newSlices);
      }
      return existingPizzaSize;
    }

    if (newInch == null
    || newCaloriesPerSlice == null
    || newSlices == null) {
      return null;
    }

    PizzaSize newPizzaSize = PizzaSize.builder()
        .inch(newInch)
        .tag(newTag)
        .caloriesPerSlice(newCaloriesPerSlice)
        .numberOfSlices(newSlices)
        .build();

    pizzaSizesRepository.save(newPizzaSize);

    log.info(
        String.format("New Pizza Size with tag %s has been added!", newTag));
    return newPizzaSize;
  }

  public PizzaSize getPizzaSizeByTag(String tag) {
    PizzaSize existingPizzaSize = pizzaSizesRepository.findByTag(tag);
    if (existingPizzaSize == null) {
      log.info(String.format("Pizza size with tag %s does not exist!", tag));
      return null;
    } else {
      return existingPizzaSize;
    }
  }

  public List<PizzaSize> getPizzaSizes() {
    return pizzaSizesRepository.findAll();
  }

  public PizzaSize deletePizzaSizeByTag(String tag)
  {
    PizzaSize existingPizzaSize = pizzaSizesRepository.findByTag(tag);
    if (existingPizzaSize == null) {
      return null;
    }
    pizzaSizesRepository.delete(existingPizzaSize);
    return existingPizzaSize;
  }
}
