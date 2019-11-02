package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

/**
 * Pizza
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-19T05:03:41.076Z")

public class Pizza   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("displayName")
  private String displayName = null;

  @JsonProperty("isGlutenFree")
  private Boolean isGlutenFree = null;

  @JsonProperty("type")
  private Type type = null;

  @JsonProperty("ownerId")
  private String ownerId = null;

  @JsonProperty("toppings")
  @Valid
  private List<Toppings> toppings = null;

  public Pizza id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Pizza displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * Get displayName
   * @return displayName
  **/
  @ApiModelProperty(value = "")


  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Pizza isGlutenFree(Boolean isGlutenFree) {
    this.isGlutenFree = isGlutenFree;
    return this;
  }

  /**
   * Get isGlutenFree
   * @return isGlutenFree
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsGlutenFree() {
    return isGlutenFree;
  }

  public void setIsGlutenFree(Boolean isGlutenFree) {
    this.isGlutenFree = isGlutenFree;
  }

  public Pizza type(Type type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Pizza ownerId(String ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  /**
   * Get ownerId
   * @return ownerId
  **/
  @ApiModelProperty(value = "")


  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public Pizza toppings(List<Toppings> toppings) {
    this.toppings = toppings;
    return this;
  }

  public Pizza addToppingsItem(Toppings toppingsItem) {
    if (this.toppings == null) {
      this.toppings = new ArrayList<Toppings>();
    }
    this.toppings.add(toppingsItem);
    return this;
  }

  /**
   * Get toppings
   * @return toppings
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Toppings> getToppings() {
    return toppings;
  }

  public void setToppings(List<Toppings> toppings) {
    this.toppings = toppings;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pizza pizza = (Pizza) o;
    return Objects.equals(this.id, pizza.id) &&
        Objects.equals(this.displayName, pizza.displayName) &&
        Objects.equals(this.isGlutenFree, pizza.isGlutenFree) &&
        Objects.equals(this.type, pizza.type) &&
        Objects.equals(this.ownerId, pizza.ownerId) &&
        Objects.equals(this.toppings, pizza.toppings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, displayName, isGlutenFree, type, ownerId, toppings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pizza {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    isGlutenFree: ").append(toIndentedString(isGlutenFree)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    ownerId: ").append(toIndentedString(ownerId)).append("\n");
    sb.append("    toppings: ").append(toIndentedString(toppings)).append("\n");
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

