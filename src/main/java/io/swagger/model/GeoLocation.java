package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * GeoLocation
 */
@Validated
@Data
@JsonDeserialize(builder = GeoLocation.GeoLocationBuilder.class)
@Builder(builderClassName = "GeoLocationBuilder", toBuilder = true)
public class GeoLocation   {
  @JsonProperty("longitude")
  private BigDecimal longitude = null;

  @JsonProperty("latitude")
  private BigDecimal latitude = null;

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
  public static class GeoLocationBuilder {
  }
}
