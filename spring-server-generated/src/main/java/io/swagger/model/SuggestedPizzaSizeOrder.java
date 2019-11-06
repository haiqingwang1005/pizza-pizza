package io.swagger.model;

import java.util.HashMap;

public class SuggestedPizzaSizeOrder {
  private HashMap<String, Integer> pizzaSizeNumberSuggestion;

  public SuggestedPizzaSizeOrder(
      HashMap<String, Integer> pizzaSizeNumberSuggestion) {
    this.pizzaSizeNumberSuggestion = pizzaSizeNumberSuggestion;
  }

  public HashMap<String, Integer> getPizzaSizeSuggestion() {
    return pizzaSizeNumberSuggestion;
  }

  public void setPizzaSizeSuggestion(
      HashMap<String, Integer> pizzaSizeSuggestion) {
    this.pizzaSizeNumberSuggestion = pizzaSizeSuggestion;
  }
}
