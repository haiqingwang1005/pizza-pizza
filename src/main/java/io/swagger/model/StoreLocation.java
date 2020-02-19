package io.swagger.model;

import io.swagger.repository.StoreLocationRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
public class StoreLocation {
  public static final String PIZZA_STORE_1_NAME = "Pizza Seattle!";
  private static final double PIZZA_STORE_1_LAT = 47.626191;
  private static final double PIZZA_STORE_1_LON = -122.339351;
  private static final String PIZZA_STORE_1_STREET = "731 Westlake Ave N";
  private static final String PIZZA_STORE_1_CITY = "Seattle";
  private static final String PIZZA_STORE_1_STATE = "WA";
  private static final String PIZZA_STORE_1_ZIP = "98109";

  public static final String PIZZA_STORE_2_NAME = "Pizza Houston!";
  private static final double PIZZA_STORE_2_LAT = 29.734648;
  private static final double PIZZA_STORE_2_LON = -95.331050;
  private static final String PIZZA_STORE_2_STREET = "724 Telephone Rd";
  private static final String PIZZA_STORE_2_CITY = "Houston";
  private static final String PIZZA_STORE_2_STATE = "TX";
  private static final String PIZZA_STORE_2_ZIP = "77023";

  public static final String PIZZA_STORE_3_NAME = "Pizza New York!";
  private static final double PIZZA_STORE_3_LAT = 40.738548;
  private static final double PIZZA_STORE_3_LON = -73.985890;
  private static final String PIZZA_STORE_3_STREET = "2 Lexington Ave";
  private static final String PIZZA_STORE_3_CITY = "New York";
  private static final String PIZZA_STORE_3_STATE = "NY";
  private static final String PIZZA_STORE_3_ZIP = "10010";

  public static void initialize(StoreLocationRepository storeLocationRepository) {

    if (storeLocationRepository.count() > 0) {
      return;
    }
    List<StoreLocation> storeLocationList = new ArrayList<>();

    StoreLocation seattleStore = new StoreLocation();
    seattleStore.setName(PIZZA_STORE_1_NAME);
    seattleStore.setGeoLocation(new GeoLocation().latitude(BigDecimal.valueOf(PIZZA_STORE_1_LAT))
        .longtitude(BigDecimal.valueOf(PIZZA_STORE_1_LON)));
    seattleStore.setAddress(new Address().address1(PIZZA_STORE_1_STREET).city(PIZZA_STORE_1_CITY)
        .state(PIZZA_STORE_1_STATE)
        .zip(PIZZA_STORE_1_ZIP));

    StoreLocation houstonStore = new StoreLocation();
    houstonStore.setName(PIZZA_STORE_2_NAME);
    houstonStore.setGeoLocation(new GeoLocation().latitude(BigDecimal.valueOf(PIZZA_STORE_2_LAT))
        .longtitude(BigDecimal.valueOf(PIZZA_STORE_2_LON)));
    houstonStore.setAddress(new Address().address1(PIZZA_STORE_2_STREET).city(PIZZA_STORE_2_CITY)
        .state(PIZZA_STORE_2_STATE)
        .zip(PIZZA_STORE_2_ZIP));

    StoreLocation newYorkStore = new StoreLocation();
    newYorkStore.setName(PIZZA_STORE_3_NAME);
    newYorkStore.setGeoLocation(new GeoLocation().latitude(BigDecimal.valueOf(PIZZA_STORE_3_LAT))
        .longtitude(BigDecimal.valueOf(PIZZA_STORE_3_LON)));
    newYorkStore.setAddress(new Address().address1(PIZZA_STORE_3_STREET).city(PIZZA_STORE_3_CITY)
        .state(PIZZA_STORE_3_STATE)
        .zip(PIZZA_STORE_3_ZIP));

    storeLocationList.add(seattleStore);
    storeLocationList.add(houstonStore);
    storeLocationList.add(newYorkStore);

    storeLocationRepository.insert(storeLocationList);
  }

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
