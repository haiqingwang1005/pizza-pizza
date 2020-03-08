package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets crustType
 */
public enum CrustType {

  ORIGINAL_PAN("Original_Pan"),
  HAND_TOSSED("Hand_Tossed"),
  THIN_N_CRISPY("Thin_N_Crispy"),
  ORIGINAL_STUFFED_CRUST("Original_Stuffed_Crust");

  private String value;

  CrustType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CrustType fromValue(String value) {
    for (CrustType b : CrustType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

