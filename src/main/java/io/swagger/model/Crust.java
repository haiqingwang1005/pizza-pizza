package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.repository.CrustsRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Gets or Sets crustType
 */
@Data
@JsonDeserialize(builder = Crust.CrustBuilder.class)
@Builder(builderClassName = "CrustBuilder", toBuilder = true)
public class Crust {
  public static void initialize(CrustsRepository crustsRepository) {
    if (crustsRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default crust!");

    List<Crust> defaults = new ArrayList<>();
    defaults.add(Crust.builder()
            .title("Original Pan")
            .name("original_pan")
            .isGlutenFree(true)
            .description("This is original pan dough.")
            .price(6.9)
            .build());

    defaults.add(Crust.builder()
            .title("Hand Toasted")
            .name("hand_toasted")
            .isGlutenFree(true)
            .description("This is hand toasted dough.")
            .price(8.9)
            .build());

    defaults.add(Crust.builder()
            .title("Whole Wheat")
            .name("whole_wheat")
            .isGlutenFree(false)
            .description("This is whole wheat dough.")
            .price(7.9)
            .build());

    defaults.add(Crust.builder()
            .title("Thin Crisp")
            .name("thin_crisp")
            .isGlutenFree(false)
            .description("This is thin crispy dough.")
            .price(6.9)
            .build());

    crustsRepository.insert(defaults);
  }

  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("isGlutenFree")
  private Boolean isGlutenFree;

  // unique
  @JsonProperty("name")
  private String name;

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("price")
  private Double price;

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class CrustBuilder {
  }
}

