package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * StoreLocation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-11T06:27:17.732Z[GMT]")
public class StoreLocation   {
  @JsonProperty("name")
  @Id
  private String name = null;

  @JsonProperty("geo_location")
  private GeoLocation geoLocation = null;

  @JsonProperty("address")
  private Address address = null;

  public StoreLocation name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Seattle Pizza Store", required = true, value = "")
  @NotNull

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StoreLocation geoLocation(GeoLocation geoLocation) {
    this.geoLocation = geoLocation;
    return this;
  }

  /**
   * Get geoLocation
   * @return geoLocation
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public GeoLocation getGeoLocation() {
    return geoLocation;
  }

  public void setGeoLocation(GeoLocation geoLocation) {
    this.geoLocation = geoLocation;
  }

  public StoreLocation address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StoreLocation storeLocation = (StoreLocation) o;
    return Objects.equals(this.name, storeLocation.name) &&
        Objects.equals(this.geoLocation, storeLocation.geoLocation) &&
        Objects.equals(this.address, storeLocation.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, geoLocation, address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StoreLocation {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    geoLocation: ").append(toIndentedString(geoLocation)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
