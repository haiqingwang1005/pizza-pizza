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
            .name("original_pan")
            .isGlutenFree(true)
            .description("This is original pan dough.")
            .build());

    defaults.add(Crust.builder()
            .name("hand_toasted")
            .isGlutenFree(true)
            .description("This is hand toasted dough.")
            .build());

    defaults.add(Crust.builder()
            .name("whole_wheat")
            .isGlutenFree(false)
            .description("This is whole wheat dough.")
            .build());

    defaults.add(Crust.builder()
            .name("thin_crisp")
            .isGlutenFree(false)
            .description("This is thin crispy dough.")
            .build());


    crustsRepository.insert(defaults);
  }

  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("isGlutenFree")
  private Boolean isGlutenFree;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

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

