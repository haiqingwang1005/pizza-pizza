package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.PizzaSizesRepository;
import io.swagger.repository.ToppingsRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Builder
@Data
public class Pizza {

  public static void initialize(
      PizzaRepository pizzaRepository,
      ToppingsRepository toppingsRepository,
      PizzaSizesRepository pizzaSizesRepository) {

    if (pizzaRepository.count() > 0) {
      return;
    }

    System.err.println("[INFO] Adding default pizza!");

    List<Toppings> defaultToppings = new ArrayList<>();

    defaultToppings.add(toppingsRepository.findByName("pepperoni"));
    defaultToppings.add(toppingsRepository.findByName("peppers"));

    PizzaSize pizzaSize = pizzaSizesRepository.findByTag("regular");

    Base base = Base.builder()
        .crustType(CrustType.ORIGINAL_STUFFED_CRUST)
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

  @JsonProperty("toppings")
  @Valid
  private List<Toppings> toppings = null;

  @JsonProperty("base")
  private Base base = null;

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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
