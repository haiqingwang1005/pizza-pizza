package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = Base.BaseBuilder.class)
@Builder(builderClassName = "BaseBuilder", toBuilder = true)
public class Base {

  @JsonProperty("pizzaSize")
  private PizzaSize pizzaSize = null;

  @JsonProperty("crust")
  private Crust crust;

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  public String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class BaseBuilder {
  }
}
