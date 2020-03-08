package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * GeoLocation
 */
@Validated
@Builder
@Data
public class GeoLocation   {
  @JsonProperty("longitude")
  private BigDecimal longitude = null;

  @JsonProperty("latitude")
  private BigDecimal latitude = null;

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
