package io.swagger.model;

import io.swagger.repository.CaloriesRepository;
import java.util.ArrayList;
import java.util.List;

public class Calories {

  static final int numberOfmealsPerDay = 3;
  static final double dailyCaloriesPerChild = 1200;
  static final double dailyCaloriesPerAdult = 2000;


  static public void initialize(CaloriesRepository caloriesRepository) {
    if (caloriesRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default calories for each type of eater!");

    List<Calories> defaults = new ArrayList<>();
    double dailyCaloriesPerChildPerMeal = dailyCaloriesPerChild/numberOfmealsPerDay;
    double dailyCaloriesPerAdultPerMeal = dailyCaloriesPerAdult/numberOfmealsPerDay;
    defaults.add(new Calories(Apetite.ADULT).numberOfCaloriesRequired(dailyCaloriesPerAdultPerMeal));
    defaults.add(new Calories(Apetite.CHILD).numberOfCaloriesRequired(dailyCaloriesPerChildPerMeal));

    caloriesRepository.insert(defaults);
  }

  private Apetite apetite;
  private double numberOfCaloriesRequired;

  public Calories() {

  }

  public Calories apetitite(Apetite apetite) {
    this.apetite = apetite;
    return this;
  }

  public Calories numberOfCaloriesRequired(double numberOfCaloriesRequired) {
    this.numberOfCaloriesRequired = numberOfCaloriesRequired;
    return this;
  }

  public Calories(Apetite apetite) {
    this.apetite = apetite;
    if(apetite.equals(Apetite.ADULT)) {
      numberOfCaloriesRequired= dailyCaloriesPerAdult/numberOfmealsPerDay;
    } else {
      numberOfCaloriesRequired = dailyCaloriesPerChild/numberOfmealsPerDay;
    }
    this.setNumberOfCaloriesRequired(numberOfCaloriesRequired);
  }

  public Apetite getApetite() {
    return apetite;
  }

  public void setApetite(Apetite apetite) {
    this.apetite = apetite;
  }

  public double getNumberOfCaloriesRequired() {
    return numberOfCaloriesRequired;
  }

  public void setNumberOfCaloriesRequired(double numberOfCaloriesRequired) {
    this.numberOfCaloriesRequired = numberOfCaloriesRequired;
  }
}
