package io.swagger.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(builder = ShopCartRequestBody.ShopCartRequestBuilder.class)
@Builder(builderClassName = "ShopCartRequestBuilder", toBuilder = true)
public class ShopCartRequestBody {
    @JsonProperty("username")
    private String username;

    @JsonProperty("pizzaList")
    private List<ShopCartRequestPizza> pizzaList;

    @JsonProperty("sideList")
    private List<ShopCartRequestSide> sidesList;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ShopCartRequestBuilder {
    }
}
