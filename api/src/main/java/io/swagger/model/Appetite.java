package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets Apetite
 */
public enum Appetite {
  ADULT("adult"),
  CHILD("child");

  private String value;

  Appetite(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Appetite fromValue(String text) {
    for (Appetite b : Appetite.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
