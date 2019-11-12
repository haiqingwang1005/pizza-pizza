package io.swagger.api;

import io.swagger.model.StoreLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.repository.StoreLocationRepository;
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.List;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-11T06:27:17.732Z[GMT]")
@Controller
public class StoresApiController implements StoresApi {
  private static final Logger log = LoggerFactory.getLogger(StoresApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private StoreLocationRepository storeLocationRepository;

  @Autowired
  public StoresApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<List<StoreLocation>> listStores() {
    List<StoreLocation> storeLocationList = storeLocationRepository.findAll();
    ResponseEntity<List<StoreLocation>> response = new ResponseEntity<List<StoreLocation>>(
        storeLocationList, HttpStatus.OK);
    return response;

  }
}
