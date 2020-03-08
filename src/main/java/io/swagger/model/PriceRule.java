package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.repository.PriceRuleRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Builder
@Data
public class PriceRule {
  public static void initialize(PriceRuleRepository priceRuleRepository) {
    if (priceRuleRepository.count() > 0) {
      return;
    }

    System.err.println("[INFO] Adding default price rule!");
    PriceRule priceRule = PriceRule
        .builder()
        .largeBasePrice(7.99)
        .mediumBasePrice(6.99)
        .smallBasePrice(5.99)
        .meatPrice(1.99)
        .vegetablePrice(1.49)
        .id(PriceRule.DEFAULT_PRICE_RULE_ID)
        .build();
    priceRuleRepository.save(priceRule);
  }

  public static final String DEFAULT_PRICE_RULE_ID = "default";

  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("vegetablePrice")
  private Double vegetablePrice;

  @JsonProperty("meatPrice")
  private Double meatPrice;

  @JsonProperty("smallBasePrice")
  private Double smallBasePrice;

  @JsonProperty("mediumBasePrice")
  private Double mediumBasePrice;

  @JsonProperty("largeBasePrice")
  private Double largeBasePrice;

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
