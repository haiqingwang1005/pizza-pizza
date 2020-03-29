package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class ShopCartPizza {
    @JsonProperty("pizzaNote")
    private String pizzaNote;

    @JsonProperty("number")
    private Integer number;

    public ShopCartPizza merge(ShopCartPizza other) {
        if (other == null) {
            return this;
        }
        setNumber(getNumber() + other.getNumber());
        if (!StringUtils.isEmpty(other.getPizzaNote())) {
            setPizzaNote(getPizzaNote() + "\n" + other.getPizzaNote());
        }
        return this;
    }
}
