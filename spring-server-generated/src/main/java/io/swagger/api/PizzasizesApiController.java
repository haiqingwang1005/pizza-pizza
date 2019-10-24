package io.swagger.api;

import io.swagger.model.PizzaSize;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PizzasizesApiController implements PizzasizesApi {

  static final Long id1 = 1L;
  static final Long id2 = 2L;
  static final Long id3 = 3L;
  static final String desc1 = "small";
  static final String desc2 = "regular";
  static final String desc3 = "large";
  static final long size1 = 11;
  static final long size2 = 13;
  static final long size3 = 17;
  static final int slices1 = 8;
  static final int slices2 = 8;
  static final int slices3 = 12;
  static final int caloriesPerSlice1 = 190;
  static final int caloriesPerSlice2 = 280;
  static final int caloriesPerSlice3 = 290;
  static final List<PizzaSize> listOfPizzaSize = new ArrayList<>();

  private static final Logger log = LoggerFactory.getLogger(PizzasizesApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;
  private Object ArrayOfPizzaSizes;

  @org.springframework.beans.factory.annotation.Autowired
  public PizzasizesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;

    PizzaSize option1 = new PizzaSize(id1,desc1,size1,slices1,caloriesPerSlice1);
    PizzaSize option2 = new PizzaSize(id2,desc2,size2,slices2,caloriesPerSlice2);
    PizzaSize option3 = new PizzaSize(id3,desc3,size3,slices3,caloriesPerSlice3);
    listOfPizzaSize.add(option1);
    listOfPizzaSize.add(option2);
    listOfPizzaSize.add(option3);
  }

  public ResponseEntity<Void> addPizzaSize(
      @ApiParam(value = "") @Valid @RequestBody PizzaSize body) {
    String accept = request.getHeader("Accept");
    for(PizzaSize option : listOfPizzaSize) {
      if(option.getId() == body.getId() || option.getDescription() == body.getDescription()) {
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
      }
    }
    long newId = body.getId();
    String newDesc = body.getDescription();
    long newSize = body.getSize();
    int newSlices = body.getnumberOfSlices();
    int newCaloriesPerSlice = body.getCaloriesPerSlice();
    PizzaSize newPizzaSize = new PizzaSize(newId, newDesc,newSize,newSlices,newCaloriesPerSlice);
    listOfPizzaSize.add(newPizzaSize);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<List<PizzaSize>> getPizzaSizes() {
    String accept = request.getHeader("Accept");
    ResponseEntity<List<PizzaSize>> responseEntity = new ResponseEntity<>(listOfPizzaSize, HttpStatus.OK);
    return responseEntity;
  }

  public ResponseEntity<PizzaSize> getSizeById(
      @ApiParam(value = "id of pizza size to return", required = true) @PathVariable("id") Long id) {
    String accept = request.getHeader("Accept");
    for(PizzaSize target : listOfPizzaSize) {
        if(target.getId().equals(id)) {
            return new ResponseEntity<PizzaSize>(target, HttpStatus.OK);
        }
    }
    return new ResponseEntity<PizzaSize>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<Void> deletePizzaSizeById(
      @ApiParam(value = "id of pizza size to delete", required = true) @PathVariable("id") Long id) {
    String accept = request.getHeader("Accept");

    for(PizzaSize option : listOfPizzaSize) {
      if(option.getId().equals(id)) {
        listOfPizzaSize.remove(option);
        return new ResponseEntity<Void>(HttpStatus.OK);
      }
    }
    return new ResponseEntity<Void>(HttpStatus.OK);

  }

}
