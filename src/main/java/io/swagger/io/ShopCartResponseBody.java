package io.swagger.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(builder = ShopCartResponseBody.ShopCartResponseBodyBuilder.class)
@Builder(builderClassName = "ShopCartResponseBodyBuilder", toBuilder = true)
public class ShopCartResponseBody {
    @JsonProperty("username")
    private String username;

    @JsonProperty("pizzas")
    private List<ShopCartResponsePizza> pizzas;

    @JsonProperty("sides")
    private List<ShopCartResponseSide> sides;

    @JsonProperty("cartPrice")
    private Double cartPrice;

    @JsonProperty("error")
    private String error;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ShopCartResponseBodyBuilder {
    }

    public static ShopCartResponseBody createError(String msg) {
        return ShopCartResponseBody.builder().error(msg).build();
    }
}
