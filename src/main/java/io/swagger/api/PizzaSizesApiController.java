package io.swagger.api;

import io.swagger.model.PizzaSize;
import io.swagger.annotations.*;
import io.swagger.service.PizzaSizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaSizesApiController implements PizzaSizesApi {

  private static final Logger log = LoggerFactory.getLogger(PizzaSizesApiController.class);

  private final PizzaSizeService pizzaSizeService;

  private final HttpServletRequest request;

  @Autowired
  public PizzaSizesApiController(HttpServletRequest request, PizzaSizeService pizzaSizeService) {
    this.request = request;
    this.pizzaSizeService = pizzaSizeService;
  }

  @Override
  public ResponseEntity<PizzaSize> addPizzaSize(
      @ApiParam(value = "") @Valid @RequestBody PizzaSize body) {

    PizzaSize pizzaSize = pizzaSizeService.addPizzaSize(body);
    if (pizzaSize == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(pizzaSize, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<PizzaSize>> getPizzaSizes() {
    List<PizzaSize> list = pizzaSizeService.getPizzaSizes();
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<PizzaSize> getSizeByTag(
      @ApiParam(value = "tag of pizza size to return",required=true) @PathVariable("tag") String tag) {
    PizzaSize existingPizzaSize = pizzaSizeService.getPizzaSizeByTag(tag);
    if (existingPizzaSize == null) {
      return new ResponseEntity<PizzaSize>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<PizzaSize>(existingPizzaSize, HttpStatus.OK);
    }
  }

  @Override
  public ResponseEntity<PizzaSize> deletePizzaSizeByTag(
      @ApiParam(value = "tag of pizza size to delete", required=true)  @PathVariable("tag") String tag) {
    PizzaSize existingPizzaSize = pizzaSizeService.deletePizzaSizeByTag(tag);
    return new ResponseEntity<PizzaSize>(existingPizzaSize, HttpStatus.OK);
  }
}
