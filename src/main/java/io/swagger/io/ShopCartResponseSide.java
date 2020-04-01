package io.swagger.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = ShopCartResponseSide.ShopCartResponseSideBuilder.class)
@Builder(builderClassName = "ShopCartResponseSideBuilder", toBuilder = true)
public class ShopCartResponseSide {
    @JsonProperty("number")
    private Integer number;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ShopCartResponseSideBuilder {
    }
}
