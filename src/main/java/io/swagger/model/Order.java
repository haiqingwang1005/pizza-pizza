package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Order {
  public static void initialize(OrderRepository orderRepository) {
    if (orderRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default order!");
    List<String> pizzaIds = new ArrayList<>();
    Order order = Order
        .builder()
        .pizzaIds(pizzaIds)
        .name("TestOrder")
        .build();
    orderRepository.save(order);
  }

  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("pizzaIds")
  @Valid
  private List<String> pizzaIds = null;

  @JsonProperty("name")
  private String name;

  public Order addPizzaIdsItem(String pizzaIdsItem) {
    if (this.pizzaIds == null) {
      this.pizzaIds = new ArrayList<>();
    }
    this.pizzaIds.add(pizzaIdsItem);
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
