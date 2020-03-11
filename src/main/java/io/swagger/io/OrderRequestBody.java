package io.swagger.io;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = OrderRequestBody.OrderDetailsResponseBuilder.class)
@Builder(builderClassName = "OrderDetailsResponseBuilder", toBuilder = true)
public class OrderRequestBody {

    @JsonPOJOBuilder(withPrefix = "")
    public static class OrderDetailsResponseBuilder {
    }
}
