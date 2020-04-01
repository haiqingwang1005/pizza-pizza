package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.repository.CrustsRepository;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.PizzaSizesRepository;
import io.swagger.repository.ToppingsRepository;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

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

        System.err.println("[INFO] Adding all pizzas!");

        List<Toppings> allToppings = toppingsRepository.findAll();
        List<Crust> allCrust = crustsRepository.findAll();
        List<PizzaSize> allPizzaSize = pizzaSizesRepository.findAll();

        for (Toppings toppings: allToppings) {
            for (Crust crust: allCrust) {
                for (PizzaSize pizzaSize: allPizzaSize) {
                    Pizza pizza = Pizza.builder()
                            .sizeName(pizzaSize.getName())
                            .crustName(crust.getName())
                            .toppingName(toppings.getName())
                            .price(pizzaSize.getPrice() + crust.getPrice() + toppings.getPrice())
                            .displayName(String.format("%s %s with %s",
                                    pizzaSize.getTitle(),
                                    crust.getTitle(),
                                    toppings.getTitle()))
                            .build();
                    pizzaRepository.save(pizza);
                }
            }
        }
    }

    @JsonProperty("id")
    @Id
    private String id;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("toppingName")
    private String toppingName;

    @JsonProperty("sizeName")
    private String sizeName;

    @JsonProperty("crustName")
    private String crustName;

    @JsonProperty("price")
    private Double price;

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
