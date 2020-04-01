package io.swagger.model;

import io.swagger.repository.CaloriesRepository;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Calories {

  static final int numberOfMealsPerDay = 3;
  static final double dailyCaloriesPerChild = 1200;
  static final double dailyCaloriesPerAdult = 2000;


  public static void initialize(CaloriesRepository caloriesRepository) {
    if (caloriesRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default calories for each type of eater!");

    List<Calories> defaults = new ArrayList<>();
    defaults.add(new Calories(Appetite.ADULT));
    defaults.add(new Calories(Appetite.CHILD));

    caloriesRepository.insert(defaults);
  }

  private Appetite appetite;
  private double numberOfCaloriesRequired;

  public Calories(Appetite appetite) {
    this.appetite = appetite;
    if(appetite.equals(Appetite.ADULT)) {
      numberOfCaloriesRequired = dailyCaloriesPerAdult / numberOfMealsPerDay;
    } else if (appetite.equals(Appetite.CHILD)) {
      numberOfCaloriesRequired = dailyCaloriesPerChild / numberOfMealsPerDay;
    }
  }

  public double getNumberOfCaloriesRequired() {
    return numberOfCaloriesRequired;
  }

}
