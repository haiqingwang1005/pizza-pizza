package io.swagger.api;

import io.swagger.model.PizzaSize;
import io.swagger.service.PizzaSizeService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity<PizzaSize> addPizzaSizes(
      @ApiParam(value = "") @Valid @RequestBody PizzaSize body) {

    PizzaSize pizzaSize = pizzaSizeService.addPizzaSize(body);
    if (pizzaSize == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(pizzaSize, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<PizzaSize>> getPizzaSizes(@ApiParam(value = "name of pizza size to return") @RequestParam(value = "name", required = false) String name) {
    List<PizzaSize> list = new ArrayList<>();
    if (!StringUtils.isEmpty(name)) {
      list.add(pizzaSizeService.getPizzaSizeByName(name));
    } else {
      list = pizzaSizeService.getPizzaSizes();
    }
    if (CollectionUtils.isEmpty(list)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<PizzaSize> deletePizzaSizeByName(
      @ApiParam(value = "name of pizza size to delete", required = true)  @RequestParam("name") String name) {
    PizzaSize existingPizzaSize = pizzaSizeService.deletePizzaSizeByName(name);
    return new ResponseEntity<PizzaSize>(existingPizzaSize, HttpStatus.OK);
  }
}
