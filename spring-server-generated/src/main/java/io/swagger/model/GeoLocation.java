package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GeoLocation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-11T06:27:17.732Z[GMT]")
public class GeoLocation   {
  @JsonProperty("longtitude")
  private BigDecimal longtitude = null;

  @JsonProperty("latitude")
  private BigDecimal latitude = null;

  public GeoLocation longtitude(BigDecimal longtitude) {
    this.longtitude = longtitude;
    return this;
  }

  /**
   * Get longtitude
   * @return longtitude
  **/
  @ApiModelProperty(example = "-122.33", value = "")

  @Valid
  public BigDecimal getLongtitude() {
    return longtitude;
  }

  public void setLongtitude(BigDecimal longtitude) {
    this.longtitude = longtitude;
  }

  public GeoLocation latitude(BigDecimal latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
  **/
  @ApiModelProperty(example = "47.606209", value = "")

  @Valid
  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeoLocation geoLocation = (GeoLocation) o;
    return Objects.equals(this.longtitude, geoLocation.longtitude) &&
        Objects.equals(this.latitude, geoLocation.latitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(longtitude, latitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeoLocation {\n");
    
    sb.append("    longtitude: ").append(toIndentedString(longtitude)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
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
