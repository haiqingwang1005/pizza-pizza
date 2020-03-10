package io.swagger.api;

import io.swagger.model.StoreLocation;
import io.swagger.service.StoreLocationService;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoresApiController implements StoresApi {
  private static final Logger log = LoggerFactory.getLogger(StoresApiController.class);

  private final HttpServletRequest request;

  private final StoreLocationService storeLocationService;

  @Autowired
  public StoresApiController(
      HttpServletRequest request,
      StoreLocationService storeLocationService) {
    this.request = request;
    this.storeLocationService = storeLocationService;
  }

  public ResponseEntity<List<StoreLocation>> listStores() {
    List<StoreLocation> storeLocationList = storeLocationService.getAllStoreLocations();
    ResponseEntity<List<StoreLocation>> response = new ResponseEntity<List<StoreLocation>>(
        storeLocationList, HttpStatus.OK);
    return response;
  }
}
