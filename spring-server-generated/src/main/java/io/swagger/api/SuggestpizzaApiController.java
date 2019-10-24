package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.PizzaSize;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-23T18:23:25.075Z[GMT]")
@Controller
public class SuggestpizzaApiController implements SuggestpizzaApi {

  static final int dailyCaloriesPerChild = 1200;
  static final int dailyCaloriesPerAdult = 2000;
  static final int numberOfmealsPerDay = 3;
  static final HashMap<String, Integer> caloriesMapping = new HashMap<>();
  private static final Logger log = LoggerFactory.getLogger(SuggestpizzaApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public SuggestpizzaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
    caloriesMapping.put("Adult", dailyCaloriesPerAdult / numberOfmealsPerDay);
    caloriesMapping.put("Child", dailyCaloriesPerChild / numberOfmealsPerDay);
  }

//  @ApiParam(value = "id of pizza size to return", required = true) @PathVariable("id") Long id

  public ResponseEntity<HashMap<String, Integer>> getOrder(
      @ApiParam(value = "the number of adult") @Valid @RequestParam(value = "Adult", required = false) Integer adult,
      @ApiParam(value = "the number of child") @Valid @RequestParam(value = "Child", required = false) Integer child) {
    String accept = request.getHeader("Accept");
    HashMap<String, Integer> res = new HashMap<>();
    int totalCaloriesRequired = 0;
    totalCaloriesRequired = caloriesMapping.get("Adult") * adult + caloriesMapping.get("Child") * child;

    int caloriesPerSmallPizza =
        PizzasizesApiController.caloriesPerSlice1 * PizzasizesApiController.slices1;
    int caloriesPerRegularPizza =
        PizzasizesApiController.caloriesPerSlice2 * PizzasizesApiController.slices2;
    int caloriesPerLargePizza =
        PizzasizesApiController.caloriesPerSlice3 * PizzasizesApiController.slices3;

    if (totalCaloriesRequired >= caloriesPerLargePizza) {
      int numberofLarger = totalCaloriesRequired / caloriesPerLargePizza;
      res.put("large", numberofLarger);
      totalCaloriesRequired -= numberofLarger * caloriesPerLargePizza;
    }
    if (totalCaloriesRequired >= caloriesPerRegularPizza) {
      int numberOfRegular = totalCaloriesRequired / caloriesPerRegularPizza;
      res.put("regular", numberOfRegular);
      totalCaloriesRequired -= numberOfRegular * caloriesPerRegularPizza;
    }

    if (totalCaloriesRequired >= caloriesPerSmallPizza) {
      int numberOfSmaller = totalCaloriesRequired / caloriesPerSmallPizza;
      res.put("small", numberOfSmaller);
      totalCaloriesRequired -= numberOfSmaller * caloriesPerSmallPizza;
    }

    if (totalCaloriesRequired > 0) {
      res.put("small", res.getOrDefault("small", 0) + 1);
      totalCaloriesRequired = 0;
    }

    return new ResponseEntity<HashMap<String, Integer>>(res, HttpStatus.OK);


  }

}
