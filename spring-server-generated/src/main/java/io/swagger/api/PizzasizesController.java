package io.swagger.api;

import io.swagger.model.PizzaSize;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repository.PizzaSizesRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-12T05:53:23.679Z[GMT]")
@Controller
public class PizzasizesController implements Pizzasizes {

  private static final Logger log = LoggerFactory.getLogger(PizzasizesController.class);

  @Autowired
  private PizzaSizesRepository pizzaSizesRepository;

  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public PizzasizesController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Void> addPizzaSize(
      @ApiParam(value = "") @Valid @RequestBody PizzaSize body) {
    String accept = request.getHeader("Accept");
    long newId = body.getId();
    String newDesc = body.getDescription();
    long newSize = body.getSize();
    int newSlices = body.getnumberOfSlices();
    int newCaloriesPerSlice = body.getCaloriesPerSlice();

    PizzaSize existingPizzaSize = pizzaSizesRepository.findById(newId);
    if (existingPizzaSize != null) {
      log.info("Pizza size with id %d already exists, please input new valid id!", newId);
      return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    PizzaSize newPizzaSize = new PizzaSize();
    newPizzaSize.id(newId).description(newDesc).size(newSize).numberOfSlices(newSlices)
        .caloriesPerSlice(newCaloriesPerSlice);
    pizzaSizesRepository.insert(newPizzaSize);

    log.info(
        String.format("New Pizza Size with id %d and name %s has been added!", newId, newDesc));
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<List<PizzaSize>> getPizzaSizes() {
    String accept = request.getHeader("Accept");
    List<PizzaSize> list = new ArrayList<>();
    Set<PizzaSize> set = new HashSet<>();
    set.addAll(pizzaSizesRepository.findAll());
    list.addAll(set);
    ResponseEntity<List<PizzaSize>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
    return responseEntity;
  }

  public ResponseEntity<PizzaSize> getSizeById(
      @ApiParam(value = "id of pizza size to return", required = true) @PathVariable("id") Long id) {
    String accept = request.getHeader("Accept");
    PizzaSize existingPizzaSize = pizzaSizesRepository.findById(id);
    if (existingPizzaSize == null) {
      log.info(String.format("Pizza size with id %d does not exist!", id));
      return new ResponseEntity<PizzaSize>(HttpStatus.BAD_REQUEST);
    } else {

      return new ResponseEntity<PizzaSize>(existingPizzaSize, HttpStatus.OK);
    }
  }

  public ResponseEntity<Void> deletePizzaSizeById(
      @ApiParam(value = "id of pizza size to delete", required = true) @PathVariable("id") Long id) {
    String accept = request.getHeader("Accept");

    PizzaSize existingPizzaSize = pizzaSizesRepository.findById(id);
    if (existingPizzaSize == null) {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    pizzaSizesRepository.delete(existingPizzaSize);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

}
