package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.constraints.*;

/**
 * Gets or Sets Type
 */
public enum Type {
  
  SPECIALTY("Specialty"),
  
  BUILDYOUROWN("BuildYourOwn");

  private String value;

  Type(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Type fromValue(String text) {
    for (Type b : Type.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

