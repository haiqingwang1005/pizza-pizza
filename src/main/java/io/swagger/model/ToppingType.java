package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets ToppingType
 */
public enum ToppingType {
  MEAT("meat"),
  VEGETABLE("vegetable");

  private String value;

  ToppingType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ToppingType fromValue(String text) {
    for (ToppingType b : ToppingType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
