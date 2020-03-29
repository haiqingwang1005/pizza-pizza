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
public class Toppings {
    public static void initialize(ToppingsRepository toppingsRepository) {
        if (toppingsRepository.count() > 0) {
            return;
        }
        System.err.println("[INFO] Adding default toppings!");

        List<Toppings> defaults = new ArrayList<>();
        defaults.add(Toppings.builder()
                .name("pepperoni")
                .isPremium(false)
                .toppingType(ToppingType.MEAT)
                .description("This is pepperoni topping.")
                .price(1.99)
                .title("Pepperoni")
                .build());

        defaults.add(Toppings.builder()
                .name("sausage")
                .isPremium(false)
                .toppingType(ToppingType.MEAT)
                .description("This is sausage topping.")
                .price(1.99)
                .title("Sausage")
                .build());

        defaults.add(Toppings.builder()
                .name("chicken")
                .isPremium(true).toppingType(ToppingType.MEAT)
                .description("This is chicken topping.")
                .price(1.99)
                .title("Chicken")
                .build());

        defaults.add(Toppings.builder()
                .name("peppers")
                .isPremium(false)
                .toppingType(ToppingType.VEGETABLE)
                .description("This is peppers topping.")
                .price(1.49)
                .title("Peppers")
                .build());

        defaults.add(Toppings.builder()
                .name("onions")
                .isPremium(false)
                .toppingType(ToppingType.VEGETABLE)
                .description("This is onions topping.")
                .price(1.49)
                .title("Onions")
                .build());

        defaults.add(Toppings.builder()
                .name("mushroom")
                .isPremium(true)
                .toppingType(ToppingType.VEGETABLE)
                .description("This is mushroom topping.")
                .price(1.49)
                .title("Mushroom")
                .build());

        toppingsRepository.insert(defaults);
    }

    @JsonProperty("id")
    @Id
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("title")
    private String title;


    @JsonProperty("isPremium")
    private Boolean isPremium;

    @JsonProperty("toppingType")
    private ToppingType toppingType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;

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
