package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@JsonDeserialize(builder = Order.OrderBuilder.class)
@Builder(builderClassName = "OrderBuilder", toBuilder = true)
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
        .username("TestOrder")
        .build();
    orderRepository.save(order);
  }

  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("pizzaIds")
  @Valid
  private List<String> pizzaIds;

  @JsonProperty("username")
  private String username;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("discount")
  private Double discount;

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
  public static class OrderBuilder {
  }
}
