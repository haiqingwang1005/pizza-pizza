package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.constraints.*;

/**
 * Gets or Sets Topping
 */
public enum Topping {
  
  PEPER("Peper"),
  
  TOMATO("Tomato"),
  
  BANANA_PEPER("Banana peper"),
  
  SPINCHI("Spinchi"),
  
  LETTCES("Lettces");

  private String value;

  Topping(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Topping fromValue(String text) {
    for (Topping b : Topping.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

