package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.CrustType;
import io.swagger.models.SauceType;
import io.swagger.repository.PizzaRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Pizza {

  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("displayName")
  private String displayName;

  @JsonProperty("isGlutenFree")
  private Boolean isGlutenFree;

  @JsonProperty("type")
  private Type type;

  @JsonProperty("ownerId")
  private String ownerId;

  @JsonProperty("toppings")
  @Valid
  private List<Toppings> toppings = null;

  @JsonProperty("base")
  private Base base = null;

  static public void initialize(PizzaRepository pizzaRepository) {
    if (pizzaRepository.count() > 0) {
      return;
    }

    System.err.println("[INFO] Adding default pizza!");
    List<Toppings> defaultToppigns = new ArrayList<>();
    defaultToppigns.add(new Toppings().name("pepperoni").isGlutenFree(true)
            .isPremium(false).toppingType(ToppingType.MEAT)
        .description("Thid is test pepperoni topping."));

    defaultToppigns.add(new Toppings().name("peppers").isGlutenFree(false)
        .isPremium(false).toppingType(ToppingType.VEGETABLE)
        .description("Thid is test peppers topping."));

    PizzaSize pizzaSize = new PizzaSize().id(1L).description("small").size(11L).numberOfSlices(8).caloriesPerSlice(190);
    Base base = Base.builder()
        .crustType(CrustType.ORIGINAL_STUFFED_CRUST)
        .sauceType(SauceType.BARBEQUE)
        .pizzaSize(pizzaSize)
        .build();
    Pizza pizza = Pizza
        .builder()
        .toppings(defaultToppigns)
        .type(Type.SPECIALTY)
        .base(base)
        .ownerId("Fan Fan")
        .id("a363c055-8601-47cc-88c7-353fb1cb66b8")
        .isGlutenFree(true)
        .displayName("FanPizza")
        .build();
    pizzaRepository.save(pizza);
  }

  public static io.swagger.models.Pizza convertToApiModel(Pizza pizza){
    io.swagger.models.Pizza result = new io.swagger.models.Pizza();
    result.setId(pizza.id);
    result.setDisplayName(pizza.displayName);
    result.setIsGlutenFree(pizza.isGlutenFree);
    result.setOwnerId(pizza.ownerId);
    result.setBase(Base.convertToApiModel(pizza.getBase()));
    result.setType(convertToApiModel(pizza.getType()));
    result.setToppings(convertToApiModel(pizza.getToppings()));
    return result;
  }

  public static Pizza convertToDaoModel(io.swagger.models.Pizza pizza){
    Pizza result = Pizza.builder()
        .id(pizza.getId())
        .displayName(pizza.getDisplayName())
        .isGlutenFree(pizza.getIsGlutenFree())
        .ownerId(pizza.getOwnerId())
        .base(Base.convertToDaoModel(pizza.getBase()))
        .type(convertToDaoModel(pizza.getType()))
        .toppings(convertToDaoModel(pizza.getToppings()))
        .build();
    return result;
  }

  private static io.swagger.models.Type convertToApiModel(Type type){
    switch (type){
      case SPECIALTY:
        return io.swagger.models.Type.SPECIALTY;
      case BUILDYOUROWN:
        return io.swagger.models.Type.BUILDYOUROWN;
      default:
        throw new UnsupportedOperationException("DAO level type is not known " + type.name());
    }
  }

  private static Type convertToDaoModel(io.swagger.models.Type type) {
    switch (type){
      case SPECIALTY:
        return Type.SPECIALTY;
      case BUILDYOUROWN:
        return Type.BUILDYOUROWN;
      default:
        throw new UnsupportedOperationException("API level type is not known " + type.name());
    }
  }

  private static io.swagger.models.Toppings convertToApiModel(Toppings toppings){
    io.swagger.models.Toppings result = new io.swagger.models.Toppings();
    result.setDescription(toppings.getDescription());
    result.setId(toppings.getId());
    result.setIsGlutenFree(toppings.isIsGlutenFree());
    result.setIsPremium(toppings.isIsPremium());
    result.setName(toppings.getName());
    result.setToppingType(convertToApiModel(toppings.getToppingType()));
    return result;
  }

  private static Toppings convertToDaoModel(io.swagger.models.Toppings toppings) {
    Toppings result = new Toppings();
    result.setDescription(toppings.getDescription());
    result.setIsGlutenFree(toppings.getIsGlutenFree());
    result.setIsPremium(toppings.getIsPremium());
    result.setName(toppings.getName());
    result.setToppingType(convertToDaoModel(toppings.getToppingType()));
    return result;
  }

  private static io.swagger.models.ToppingType convertToApiModel(ToppingType toppingType){
    switch (toppingType){
      case VEGETABLE:
        return io.swagger.models.ToppingType.VEGETABLE;
      case MEAT:
        return io.swagger.models.ToppingType.MEAT;
      default:
        throw new UnsupportedOperationException("DAO level topping type is not known " + toppingType.name());
    }
  }

  private static ToppingType convertToDaoModel(io.swagger.models.ToppingType toppingType) {
    switch (toppingType){
      case VEGETABLE:
        return ToppingType.VEGETABLE;
      case MEAT:
        return ToppingType.MEAT;
      default:
        throw new UnsupportedOperationException("API level topping type is not known " + toppingType.name());
    }
  }
  private static List<io.swagger.models.Toppings> convertToApiModel(List<Toppings> toppings){
    List<io.swagger.models.Toppings> result = new ArrayList<>();
    for(Toppings t : toppings) {
      result.add(convertToApiModel(t));
    }

    return result;
  }

  private static List<Toppings> convertToDaoModel(List<io.swagger.models.Toppings> toppings) {
    List<Toppings> result = new ArrayList<>();
    for(io.swagger.models.Toppings t : toppings) {
      result.add(convertToDaoModel(t));
    }

    return result;
  }
}
