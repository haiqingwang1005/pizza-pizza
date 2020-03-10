package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.repository.PizzaSizesRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

/**
 * PizzaSize
 */
@Validated
@Data
@JsonDeserialize(builder = PizzaSize.PizzaSizeBuilder.class)
@Builder(builderClassName = "PizzaSizeBuilder", toBuilder = true)
public class PizzaSize {
  static public void initialize(PizzaSizesRepository pizzaSizesRepository) {
    if (pizzaSizesRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default piazza sizes!");

    pizzaSizesRepository.save(PizzaSize.builder()
        .tag("small")
        .inch(11L)
        .numberOfSlices(8)
        .caloriesPerSlice(190)
        .build());

    pizzaSizesRepository.save(PizzaSize.builder()
        .tag("regular")
        .inch(13L)
        .numberOfSlices(8)
        .caloriesPerSlice(280)
        .build());

    pizzaSizesRepository.save(PizzaSize.builder()
        .tag("large")
        .inch(17L)
        .numberOfSlices(12)
        .caloriesPerSlice(290)
        .build());
  }

  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("tag")
  private String tag;

  @JsonProperty("inch")
  private Long inch;

  @JsonProperty("numberOfSlices")
  private Integer numberOfSlices;

  @JsonProperty("caloriesPerSlice")
  private Integer caloriesPerSlice;

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  public String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class PizzaSizeBuilder {
  }
}
