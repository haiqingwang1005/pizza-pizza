package io.swagger.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.model.Pizza;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = ShopCartResponsePizza.ShopCartResponsePizzaBuilder.class)
@Builder(builderClassName = "ShopCartResponsePizzaBuilder", toBuilder = true)
public class ShopCartResponsePizza {
    @JsonProperty("pizza")
    private Pizza pizza;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("pizzaNote")
    private String pizzaNote;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ShopCartResponsePizzaBuilder {
    }
}
