package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.repository.CrustsRepository;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.PizzaSizesRepository;
import io.swagger.repository.ToppingsRepository;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@JsonDeserialize(builder = Pizza.PizzaBuilder.class)
@Builder(builderClassName = "PizzaBuilder", toBuilder = true)
public class Pizza {

  public static void initialize(
      PizzaRepository pizzaRepository,
      ToppingsRepository toppingsRepository,
      CrustsRepository crustsRepository,
      PizzaSizesRepository pizzaSizesRepository) {

    if (pizzaRepository.count() > 0) {
      return;
    }

    System.err.println("[INFO] Adding default pizza!");

    List<Toppings> defaultToppings = new ArrayList<>();

    defaultToppings.add(toppingsRepository.findByName("pepperoni"));
    defaultToppings.add(toppingsRepository.findByName("peppers"));

    PizzaSize pizzaSize = pizzaSizesRepository.findByTag("regular");

    Crust crust = crustsRepository.findByName("original_pan");

    Base base = Base.builder()
        .crust(crust)
        .pizzaSize(pizzaSize)
        .build();
    Pizza pizza = Pizza
        .builder()
        .toppings(defaultToppings)
        .base(base)
        .ownerId("TestOwner")
        .displayName("TestPizza")
        .build();
    pizzaRepository.save(pizza);
  }

  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("ownerId")
  private String ownerId;

  @JsonProperty("displayName")
  private String displayName;

  // Toppings should be at least one, at most two.
  @JsonProperty("toppings")
  @Valid
  private List<Toppings> toppings;

  @JsonProperty("base")
  private Base base;

  public Pizza addToppingsItem(Toppings toppingsItem) {
    if (this.toppings == null) {
      this.toppings = new ArrayList<>();
    }
    this.toppings.add(toppingsItem);
    return this;
  }

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
  public static class PizzaBuilder {
  }
}
