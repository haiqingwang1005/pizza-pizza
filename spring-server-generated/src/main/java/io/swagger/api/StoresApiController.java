package io.swagger.api;

import io.swagger.model.Address;
import io.swagger.model.GeoLocation;
import io.swagger.model.StoreLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-11T06:27:17.732Z[GMT]")
@Controller
public class StoresApiController implements StoresApi {

  static final String PIZZA_STORE_1_NAME = "Pizza Seattle!";
  private static final double PIZZA_STORE_1_LAT = 47.626191;
  private static final double PIZZA_STORE_1_LON = -122.339351;
  private static final String PIZZA_STORE_1_STREET = "731 Westlake Ave N";
  private static final String PIZZA_STORE_1_CITY = "Seattle";
  private static final String PIZZA_STORE_1_STATE = "WA";
  private static final String PIZZA_STORE_1_ZIP = "98109";

  static final String PIZZA_STORE_2_NAME = "Pizza Houston!";
  private static final double PIZZA_STORE_2_LAT = 29.734648;
  private static final double PIZZA_STORE_2_LON = -95.331050;
  private static final String PIZZA_STORE_2_STREET = "724 Telephone Rd";
  private static final String PIZZA_STORE_2_CITY = "Houston";
  private static final String PIZZA_STORE_2_STATE = "TX";
  private static final String PIZZA_STORE_2_ZIP = "77023";

  static final String PIZZA_STORE_3_NAME = "Pizza New York!";
  private static final double PIZZA_STORE_3_LAT = 40.738548;
  private static final double PIZZA_STORE_3_LON = -73.985890;
  private static final String PIZZA_STORE_3_STREET = "2 Lexington Ave";
  private static final String PIZZA_STORE_3_CITY = "New York";
  private static final String PIZZA_STORE_3_STATE = "NY";
  private static final String PIZZA_STORE_3_ZIP = "10010";

  private static final Logger log = LoggerFactory.getLogger(StoresApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  public StoresApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<List<StoreLocation>> listStores() {
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

    ResponseEntity<List<StoreLocation>> response = new ResponseEntity<List<StoreLocation>>(
        storeLocationList, HttpStatus.OK);
    return response;

  }
}
