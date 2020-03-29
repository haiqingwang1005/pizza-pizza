package io.swagger.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = ShopCartRequestPizza.ShopCartRequestPizzaBuilder.class)
@Builder(builderClassName = "ShopCartRequestPizzaBuilder", toBuilder = true)
public class ShopCartRequestPizza {
    /**
     * If the pizzaId presents, it will ignore the following crustName, sizeName and toppingName,
     */
    @JsonProperty("pizzaId")
    private String pizzaId;

    @JsonProperty("crustName")
    private String crustName;

    @JsonProperty("sizeName")
    private String sizeName;

    @JsonProperty("toppingName")
    private String toppingName;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("pizzaNote")
    private String pizzaNote;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ShopCartRequestPizzaBuilder {
    }
}
