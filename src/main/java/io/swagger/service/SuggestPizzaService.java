package io.swagger.service;

import io.swagger.repository.CaloriesRepository;
import io.swagger.repository.PizzaSizesRepository;
import io.swagger.model.Appetite;
import io.swagger.model.Customer;
import io.swagger.model.PizzaSize;
import io.swagger.model.PizzaSuggestion;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuggestPizzaService {
  private final PizzaSizesRepository pizzaSizesRepository;

  private final CaloriesRepository caloriesRepository;

  @Autowired
  public SuggestPizzaService(PizzaSizesRepository pizzaSizesRepository, CaloriesRepository caloriesRepository) {
    this.pizzaSizesRepository = pizzaSizesRepository;
    this.caloriesRepository = caloriesRepository;
  }

  public PizzaSuggestion getSuggestion(Customer customer)
  {
    double totalCaloriesRequired =
        caloriesRepository.getCalorieByAppetite(Appetite.ADULT).getNumberOfCaloriesRequired() * customer.getNumberOfAdult()
            + caloriesRepository.getCalorieByAppetite(Appetite.CHILD).getNumberOfCaloriesRequired() * customer.getNumberOfChild();

    List<PizzaSize> pizzaSizesList = pizzaSizesRepository.findAll();

    pizzaSizesList.sort((p1, p2) -> p2.getCaloriesPerSlice() * p2.getNumberOfSlices()
        - p1.getCaloriesPerSlice() * p1.getNumberOfSlices());


    HashMap<String, Integer> res = new HashMap<>();

    if (pizzaSizesList.isEmpty()) {
      return PizzaSuggestion.builder().suggestion(res).build();
    }

    for (PizzaSize pizzaSize: pizzaSizesList) {
      int totalCaloriesForCurrentPizzaType =
          pizzaSize.getCaloriesPerSlice() * pizzaSize.getNumberOfSlices();

      if (totalCaloriesRequired <= 0) {
        continue;
      }

      if (totalCaloriesRequired >= totalCaloriesForCurrentPizzaType) {
        int numberOfCurrentType = (int) (totalCaloriesRequired / totalCaloriesForCurrentPizzaType);
        res.put(pizzaSize.getName(), numberOfCurrentType);
        totalCaloriesRequired -= numberOfCurrentType * totalCaloriesForCurrentPizzaType;
      }

      if (totalCaloriesRequired > 0) {
        PizzaSize smallestPizzaSize = pizzaSizesList.get(pizzaSizesList.size() - 1);
        res.put(smallestPizzaSize.getName(),
            res.getOrDefault(smallestPizzaSize.getName(), 0) + 1);
      }
    }
    return PizzaSuggestion.builder().suggestion(res).build();
  }
}
