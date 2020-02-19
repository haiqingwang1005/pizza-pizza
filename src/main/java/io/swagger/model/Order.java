package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.PriceRuleRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Order {
  @Id
  @JsonProperty("id")
  private String id;

  @JsonProperty("pizzaIds")
  @Valid
  private List<String> pizzaIds = null;

  @JsonProperty("name")
  private String name;

  static public void initialize(OrderRepository orderRepository) {
    if (orderRepository.count() > 0) {
      return;
    }

    System.err.println("[INFO] Adding default order!");
    List<String> pizzaIds = new ArrayList<>();
    pizzaIds.add("a363c055-8601-47cc-88c7-353fb1cb66b8");
    Order order = Order
        .builder()
        .id("d6c79fbc-5e6b-48bb-bfdd-865fc34af991")
        .pizzaIds(pizzaIds)
        .name("Fan Order")
        .build();
    orderRepository.save(order);
  }

  public static io.swagger.models.Order convertToApiModel(Order order){
    io.swagger.models.Order result = new io.swagger.models.Order();
    result.setId(order.id);
    result.setName(order.name);
    result.setPizzaIds(order.pizzaIds);
    return result;
  }

  public static Order convertToDaoModel(io.swagger.models.Order order){
    Order result = Order.builder()
        .id(order.getId())
        .name(order.getName())
        .pizzaIds(order.getPizzaIds())
        .build();
    return result;
  }
}
