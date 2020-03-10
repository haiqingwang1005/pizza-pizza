package io.swagger.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.repository.ToppingsRepository;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

/**
 * Toppings
 */
@Validated
@Data
@JsonDeserialize(builder = Toppings.ToppingsBuilder.class)
@Builder(builderClassName = "ToppingsBuilder", toBuilder = true)
public class Toppings   {
  public static void initialize(ToppingsRepository toppingsRepository) {
    if (toppingsRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default toppings!");

    List<Toppings> defaults = new ArrayList<>();
    defaults.add(Toppings.builder()
        .name("pepperoni")
        .isGlutenFree(true)
        .isPremium(false)
        .toppingType(ToppingType.MEAT)
        .description("This is pepperoni topping.")
        .build());

    defaults.add(Toppings.builder()
        .name("sausage")
        .isGlutenFree(true)
        .isPremium(false)
        .toppingType(ToppingType.MEAT)
        .description("This is sausage topping.")
        .build());

    defaults.add(Toppings.builder()
        .name("chicken")
        .isGlutenFree(true)
        .isPremium(true).toppingType(ToppingType.MEAT)
        .description("This is chicken topping.")
        .build());

    defaults.add(Toppings.builder()
        .name("peppers")
        .isGlutenFree(false)
        .isPremium(false)
        .toppingType(ToppingType.VEGETABLE)
        .description("This is peppers topping.")
        .build());

    defaults.add(Toppings.builder()
        .name("onions")
        .isGlutenFree(true)
        .isPremium(false)
        .toppingType(ToppingType.VEGETABLE)
        .description("This is onions topping.")
        .build());

    defaults.add(Toppings.builder()
        .name("mushroom")
        .isGlutenFree(false)
        .isPremium(true)
        .toppingType(ToppingType.VEGETABLE)
        .description("This is mushroom topping.")
        .build());

    toppingsRepository.insert(defaults);
  }

  @JsonProperty("id")
  @Id
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("isGlutenFree")
  private Boolean isGlutenFree = null;

  @JsonProperty("isPremium")
  private Boolean isPremium = null;

  @JsonProperty("toppingType")
  private ToppingType toppingType = null;

  @JsonProperty("description")
  private String description = null;

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class ToppingsBuilder {
  }
}
