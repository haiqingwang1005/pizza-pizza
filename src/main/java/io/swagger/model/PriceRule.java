package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.repository.PizzaSizesRepository;
import io.swagger.repository.PriceRuleRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class PriceRule {

  public static final String DEFAULT_PRICE_RULE_ID = "default";

  @JsonProperty("id")
  @Id
  private String id;

  @JsonProperty("vagetablePrice")
  private Double vagetablePrice;

  @JsonProperty("meatPrice")
  private Double meatPrice;

  @JsonProperty("smallBasePrice")
  private Double smallBasePrice;

  @JsonProperty("mediumBasePrice")
  private Double mediumBasePrice;

  @JsonProperty("largeBasePrice")
  private Double largeBasePrice;

  static public void initialize(PriceRuleRepository priceRuleRepository) {
    if (priceRuleRepository.count() > 0) {
      return;
    }

    System.err.println("[INFO] Adding default price rule!");
    PriceRule priceRule = PriceRule
        .builder()
        .largeBasePrice(new Double(7.99))
        .mediumBasePrice(new Double(6.99))
        .smallBasePrice(new Double(5.99))
        .meatPrice(new Double(1.99))
        .vagetablePrice(new Double(1.49))
        .id(PriceRule.DEFAULT_PRICE_RULE_ID)
        .build();
    priceRuleRepository.save(priceRule);
  }
}
