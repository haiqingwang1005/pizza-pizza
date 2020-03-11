package io.swagger.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.model.Promotion;
import java.util.List;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = OrderRequestBody.OrderDetailsResponseBuilder.class)
@Builder(builderClassName = "OrderDetailsResponseBuilder", toBuilder = true)
public class OrderRequestBody {
    @JsonProperty("pizzaIds")
    @Valid
    private List<String> pizzaIds;

    @JsonProperty
    private String username;

    @JsonProperty("promotionCode")
    private String promotionCode;

    @JsonPOJOBuilder(withPrefix = "")
    public static class OrderDetailsResponseBuilder {
    }
}