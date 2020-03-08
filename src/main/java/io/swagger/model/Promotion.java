package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.repository.PromotionRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Promotion
 */
@Builder
@Data
public class Promotion {

  public static void initialize(PromotionRepository promotionRepository) {
    if (promotionRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default promotions!");

    List<Promotion> defaults = new ArrayList<>();
    defaults.add(
        Promotion.builder()
            .code("keeshond")
            .discount(BigDecimal.valueOf(0.5))
            .build());

    defaults.add(
        Promotion.builder()
            .code("dharma")
            .discount(BigDecimal.valueOf(0.6))
            .build());

    promotionRepository.insert(defaults);
  }

  @JsonProperty("id")
  @Id
  private String id = null;

  @JsonProperty("code")
  private String code;

  @JsonProperty("discount")
  private BigDecimal discount;

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

