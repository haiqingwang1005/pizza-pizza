package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Data
@Builder
public class ShopCart {
    @JsonProperty("id")
    @Id
    private String id;

    @JsonProperty("username")
    private String username;

    // Key is pizza id, value is numbers.
    @JsonProperty("pizzas")
    private Map<String, ShopCartPizza> pizzas;

    @JsonProperty("sides")
    private Map<String, ShopCartSide> sides;
}
