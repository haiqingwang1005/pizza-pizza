package io.swagger.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.repository.StoreLocationRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;


/**
 * StoreLocation
 */
@Validated
@Data
@JsonDeserialize(builder = StoreLocation.StoreLocationBuilder.class)
@Builder(builderClassName = "StoreLocationBuilder", toBuilder = true)
public class StoreLocation {
  private static final String PIZZA_STORE_1_ID = "seattle";
  public static final String PIZZA_STORE_1_NAME = "Pizza Seattle!";
  private static final BigDecimal PIZZA_STORE_1_LAT = BigDecimal.valueOf(47.626191);
  private static final BigDecimal PIZZA_STORE_1_LON = BigDecimal.valueOf(-122.339351);
  private static final String PIZZA_STORE_1_STREET = "731 Westlake Ave N";
  private static final String PIZZA_STORE_1_CITY = "Seattle";
  private static final String PIZZA_STORE_1_STATE = "WA";
  private static final String PIZZA_STORE_1_ZIP = "98109";

  private static final String PIZZA_STORE_2_ID = "houston";
  public static final String PIZZA_STORE_2_NAME = "Pizza Houston!";
  private static final BigDecimal PIZZA_STORE_2_LAT = BigDecimal.valueOf(29.734648);
  private static final BigDecimal PIZZA_STORE_2_LON = BigDecimal.valueOf(-95.331050);
  private static final String PIZZA_STORE_2_STREET = "724 Telephone Rd";
  private static final String PIZZA_STORE_2_CITY = "Houston";
  private static final String PIZZA_STORE_2_STATE = "TX";
  private static final String PIZZA_STORE_2_ZIP = "77023";

  private static final String PIZZA_STORE_3_ID = "new_york";
  public static final String PIZZA_STORE_3_NAME = "Pizza New York!";
  private static final BigDecimal PIZZA_STORE_3_LAT = BigDecimal.valueOf(40.738548);
  private static final BigDecimal PIZZA_STORE_3_LON = BigDecimal.valueOf(-73.985890);
  private static final String PIZZA_STORE_3_STREET = "2 Lexington Ave";
  private static final String PIZZA_STORE_3_CITY = "New York";
  private static final String PIZZA_STORE_3_STATE = "NY";
  private static final String PIZZA_STORE_3_ZIP = "10010";

  public static void initialize(StoreLocationRepository storeLocationRepository) {

    if (storeLocationRepository.count() > 0) {
      return;
    }
    List<StoreLocation> storeLocationList = new ArrayList<>();

    StoreLocation seattleStore = StoreLocation.builder()
        .id(PIZZA_STORE_1_ID)
        .name(PIZZA_STORE_1_NAME)
        .geoLocation(GeoLocation.builder()
            .latitude(PIZZA_STORE_1_LAT)
            .longitude(PIZZA_STORE_1_LON)
            .build())
        .address(Address.builder()
            .address1(PIZZA_STORE_1_STREET)
            .city(PIZZA_STORE_1_CITY)
            .state(PIZZA_STORE_1_STATE)
            .zip(PIZZA_STORE_1_ZIP)
            .build())
        .build();


    StoreLocation houstonStore = StoreLocation.builder()
        .id(PIZZA_STORE_2_ID)
        .name(PIZZA_STORE_2_NAME)
        .geoLocation(GeoLocation.builder()
            .latitude(PIZZA_STORE_2_LAT)
            .longitude(PIZZA_STORE_2_LON)
            .build())
        .address(Address.builder()
            .address1(PIZZA_STORE_2_STREET)
            .city(PIZZA_STORE_2_CITY)
            .state(PIZZA_STORE_2_STATE)
            .zip(PIZZA_STORE_2_ZIP)
            .build())
        .build();

    StoreLocation newYorkStore = StoreLocation.builder()
        .id(PIZZA_STORE_3_ID)
        .name(PIZZA_STORE_3_NAME)
        .geoLocation(GeoLocation.builder()
            .latitude(PIZZA_STORE_3_LAT)
            .longitude(PIZZA_STORE_3_LON)
            .build())
        .address(Address.builder()
            .address1(PIZZA_STORE_3_STREET)
            .city(PIZZA_STORE_3_CITY)
            .state(PIZZA_STORE_3_STATE)
            .zip(PIZZA_STORE_3_ZIP)
            .build())
        .build();

    storeLocationList.add(seattleStore);
    storeLocationList.add(houstonStore);
    storeLocationList.add(newYorkStore);

    storeLocationRepository.insert(storeLocationList);
  }

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("geo_location")
  private GeoLocation geoLocation;

  @JsonProperty("address")
  private Address address;

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
  public static class StoreLocationBuilder {
  }
}
