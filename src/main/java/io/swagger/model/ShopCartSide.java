package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopCartSide {

    @JsonProperty("sideNote")
    private String sideNote;

    @JsonProperty("number")
    private Integer number;
}
