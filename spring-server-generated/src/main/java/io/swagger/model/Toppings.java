package io.swagger.model;

import io.swagger.repository.ToppingsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Toppings
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-27T04:50:36.457Z[GMT]")
public class Toppings   {
  static public void initialize(ToppingsRepository toppingsRepository) {
    if (toppingsRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default toppings!");

    List<Toppings> defaults = new ArrayList<>();
    defaults.add(new Toppings().name("pepperoni").isGlutenFree(true)
        .isPremium(false).toppingType(ToppingType.MEAT)
        .description("Thid is test pepperoni topping."));

    defaults.add(new Toppings().name("sausage").isGlutenFree(true)
        .isPremium(false).toppingType(ToppingType.MEAT)
        .description("Thid is test sausage topping."));

    defaults.add(new Toppings().name("chicken").isGlutenFree(true)
        .isPremium(true).toppingType(ToppingType.MEAT)
        .description("Thid is test chicken topping."));

    defaults.add(new Toppings().name("peppers").isGlutenFree(false)
        .isPremium(false).toppingType(ToppingType.VEGETABLE)
        .description("Thid is test peppers topping."));

    defaults.add(new Toppings().name("onions").isGlutenFree(true)
        .isPremium(false).toppingType(ToppingType.VEGETABLE)
        .description("Thid is onions peppers topping."));

    defaults.add(new Toppings().name("mushroom").isGlutenFree(false)
        .isPremium(true).toppingType(ToppingType.VEGETABLE)
        .description("Thid is test mushroom topping."));

    toppingsRepository.insert(defaults);
  }

  @JsonProperty("id")
  @Id
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("isGlutenFree")
  private Boolean isGlutenFree = null;

  @JsonProperty("isPremium")
  private Boolean isPremium = null;

  @JsonProperty("toppingType")
  private ToppingType toppingType = null;

  @JsonProperty("description")
  private String description = null;

  public Toppings id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @ApiModelProperty(example = "5dbcdb313f197a67bf4d66cf", required = false, value = "")
  public String getId() {
    return id;
  }

  public Toppings name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @ApiModelProperty(example = "Beef", required = true, value = "")
  @NotNull

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Toppings isGlutenFree(Boolean isGlutenFree) {
    this.isGlutenFree = isGlutenFree;
    return this;
  }

  /**
   * Get isGlutenFree
   * @return isGlutenFree
   **/
  @ApiModelProperty(example = "true", required = true, value = "")
  @NotNull

  public Boolean isIsGlutenFree() {
    return isGlutenFree;
  }

  public void setIsGlutenFree(Boolean isGlutenFree) {
    this.isGlutenFree = isGlutenFree;
  }

  public Toppings isPremium(Boolean isPremium) {
    this.isPremium = isPremium;
    return this;
  }

  /**
   * Get isPremium
   * @return isPremium
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public Boolean isIsPremium() {
    return isPremium;
  }

  public void setIsPremium(Boolean isPremium) {
    this.isPremium = isPremium;
  }

  public Toppings toppingType(ToppingType toppingType) {
    this.toppingType = toppingType;
    return this;
  }

  /**
   * Get toppingType
   * @return toppingType
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public ToppingType getToppingType() {
    return toppingType;
  }

  public void setToppingType(ToppingType toppingType) {
    this.toppingType = toppingType;
  }

  public Toppings description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  @ApiModelProperty(example = "Gluten free premium beef as meat topping", required = true, value = "")
  @NotNull

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Toppings toppings = (Toppings) o;
    return Objects.equals(this.name, toppings.name) &&
        Objects.equals(this.isGlutenFree, toppings.isGlutenFree) &&
        Objects.equals(this.isPremium, toppings.isPremium) &&
        Objects.equals(this.toppingType, toppings.toppingType) &&
        Objects.equals(this.description, toppings.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, isGlutenFree, isPremium, toppingType, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Toppings {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    isGlutenFree: ").append(toIndentedString(isGlutenFree)).append("\n");
    sb.append("    isPremium: ").append(toIndentedString(isPremium)).append("\n");
    sb.append("    toppingType: ").append(toIndentedString(toppingType)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
