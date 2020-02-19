package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.CrustType;
import io.swagger.models.SauceType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Base {
  @JsonProperty("pizzaSize")
  private PizzaSize pizzaSize = null;

  @JsonProperty("sauceType")
  private SauceType sauceType;

  @JsonProperty("crustType")
  private CrustType crustType;

  public static io.swagger.models.Base convertToApiModel(Base base){
    io.swagger.models.Base result = new io.swagger.models.Base();
    result.setCrustType(base.crustType);
    result.setSauceType(base.sauceType);
    result.setPizzaSize(convertToApiModel(base.pizzaSize));
    return result;
  }

  public static Base convertToDaoModel(io.swagger.models.Base base) {
    Base result = Base.builder()
        .crustType(base.getCrustType())
        .sauceType(base.getSauceType())
        .pizzaSize(convertToDaoModel(base.getPizzaSize()))
        .build();
    return result;
  }

  private static io.swagger.models.PizzaSize convertToApiModel(PizzaSize pizzaSize){
    io.swagger.models.PizzaSize result = new io.swagger.models.PizzaSize();
    result.setCaloriesPerSlice(new Integer(pizzaSize.getCaloriesPerSlice()));
    result.setNoOfSlices(new Integer(pizzaSize.getnumberOfSlices()));
    result.setDescription(pizzaSize.getDescription());
    result.setId(pizzaSize.getId());
    result.setSize(pizzaSize.getSize());
    return result;
  }

  public static PizzaSize convertToDaoModel(io.swagger.models.PizzaSize pizzaSize) {
    PizzaSize result = new PizzaSize();
    result.setCaloriesPerSlice(pizzaSize.getCaloriesPerSlice().intValue());
    result.setnumberOfSlices(pizzaSize.getNoOfSlices().intValue());
    result.setDescription(pizzaSize.getDescription());
    result.setId(pizzaSize.getId());
    result.setSize(pizzaSize.getSize());
    return result;
  }

}
