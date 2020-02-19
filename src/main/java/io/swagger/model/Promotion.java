package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.repository.PromotionRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.data.annotation.Id;

/**
 * Promotion
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-11-11T23:28:07.488837-08:00[America/Los_Angeles]")

public class Promotion {

  static public void initialize(PromotionRepository promotionRepository) {
    if (promotionRepository.count() > 0) {
      return;
    }
    System.err.println("[INFO] Adding default toppings!");

    List<Promotion> defaults = new ArrayList<>();
    defaults.add(new Promotion().code("keeshond").discount(BigDecimal.valueOf(0.5)));

    defaults.add(new Promotion().code("dharma").discount(BigDecimal.valueOf(0.6)));

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
   * Get id
   * @return id
   **/
  @ApiModelProperty(example = "5dbcdb313f197a67bf4d66cf", required = false, value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Promotion code(String code) {
    this.code = code;
    return this;
  }
  public Promotion id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get code
   * @return code
  */
  @ApiModelProperty(value = "")


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Promotion discount(BigDecimal discount) {
    this.discount = discount;
    return this;
  }

  /**
   * Get discount
   * @return discount
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Promotion promotion = (Promotion) o;
    return Objects.equals(this.code, promotion.code) &&
        Objects.equals(this.discount, promotion.discount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, discount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Promotion {\n");

    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    discount: ").append(toIndentedString(discount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

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

