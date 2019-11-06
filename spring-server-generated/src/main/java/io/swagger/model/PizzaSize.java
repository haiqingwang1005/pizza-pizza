package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.repository.PizzaSizesRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * PizzaSize
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-12T05:53:23.679Z[GMT]")
public class PizzaSize   {
  static public void initialize(PizzaSizesRepository pizzaSizesRepository) {
    if (pizzaSizesRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default piazza sizes!");

    List<PizzaSize> defaults = new ArrayList<>();
    defaults.add(new PizzaSize().id(1L).description("small").size(11L).numberOfSlices(8).caloriesPerSlice(190));
    defaults.add(new PizzaSize().id(2L).description("regular").size(13L).numberOfSlices(8).caloriesPerSlice(280));
    defaults.add(new PizzaSize().id(3L).description("large").size(17L).numberOfSlices(12).caloriesPerSlice(290));
    pizzaSizesRepository.insert(defaults);
  }

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("size")
  private Long size = null;

  @JsonProperty("numberOfSlices")
  private Integer numberOfSlices = null;

  @JsonProperty("caloriesPerSlice")
  private Integer caloriesPerSlice = null;

  public PizzaSize(Long id, String description, Long size, int numberOfSlices, int caloriesPerSlice) {
    this.id = id;
    this.description = description;
    this.size = size;
    this.numberOfSlices = numberOfSlices;
    this.caloriesPerSlice = caloriesPerSlice;
  }

  public PizzaSize() {

  }

  public PizzaSize id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1", required = true, value = "")
  @NotNull

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PizzaSize description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "small", required = true, value = "")
  @NotNull

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PizzaSize size(Long size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
  **/
  @ApiModelProperty(example = "7", required = true, value = "")
  @NotNull

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }


  public PizzaSize caloriesPerSlice(int  caloriesPerSlice) {
    this.caloriesPerSlice = caloriesPerSlice;
    return this;
  }

  /**
   * Get calories per slice
   * @return calories per slice
   **/
  @ApiModelProperty(example = "190", required = true, value = "")
  @NotNull



  public int getCaloriesPerSlice() {
    return caloriesPerSlice;
  }

  public void setCaloriesPerSlice(int caloriesPerSlice) {
    this.caloriesPerSlice = caloriesPerSlice;
  }

  public PizzaSize numberOfSlices(int numberOfSlices) {
    this.numberOfSlices = numberOfSlices;
    return this;
  }

  /**
   * Get slices
   * @return slices
   **/
  @ApiModelProperty(example = "8", required = true, value = "")
  @NotNull

  public int getnumberOfSlices() {
    return numberOfSlices;
  }

  public void setnumberOfSlices(int numberOfSlices) {
    this.numberOfSlices = numberOfSlices;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PizzaSize pizzaSize = (PizzaSize) o;
    return Objects.equals(this.id, pizzaSize.id) &&
        Objects.equals(this.description, pizzaSize.description) &&
        Objects.equals(this.size, pizzaSize.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, size);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PizzaSize {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
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
