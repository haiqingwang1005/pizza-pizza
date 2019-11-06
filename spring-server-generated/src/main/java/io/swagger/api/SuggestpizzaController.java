package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Apetite;
import io.swagger.model.PizzaSize;
import io.swagger.repository.CaloriesRepository;
import io.swagger.repository.PizzaSizesRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-23T18:23:25.075Z[GMT]")
@Controller
public class SuggestpizzaController implements Suggestpizza {

  private static final Logger log = LoggerFactory.getLogger(SuggestpizzaController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @Autowired
  private PizzaSizesRepository pizzaSizesRepository;

  @Autowired
  private CaloriesRepository caloriesRepository;

  @org.springframework.beans.factory.annotation.Autowired
  public SuggestpizzaController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }


  public ResponseEntity<HashMap<String, Integer>> getOrder(
      @ApiParam(value = "the number of adult") @Valid @RequestParam(value = "Adult", required = false) Integer adult,
      @ApiParam(value = "the number of child") @Valid @RequestParam(value = "Child", required = false) Integer child) {
    String accept = request.getHeader("Accept");

    double totalCaloriesRequired =
        caloriesRepository.getCalorieByApetite(Apetite.ADULT).getNumberOfCaloriesRequired() * adult
            + caloriesRepository.getCalorieByApetite(Apetite.CHILD).getNumberOfCaloriesRequired()
            * child;

    List<PizzaSize> pizzaSizesList = new ArrayList<>();
    Set<PizzaSize> allPizzaSizes = new HashSet<>();
    allPizzaSizes.addAll(pizzaSizesRepository.findAll());
    pizzaSizesList.addAll(allPizzaSizes);

    System.out.println("I am printing the number of calories required " + totalCaloriesRequired);
    Collections.sort(pizzaSizesList, new Comparator<PizzaSize>() {
      @Override
      public int compare(PizzaSize p1, PizzaSize p2) {
        return p2.getCaloriesPerSlice() * p2.getnumberOfSlices() - p1.getCaloriesPerSlice() * p1
            .getnumberOfSlices();
      }
    });

    for (PizzaSize ps : pizzaSizesList) {
      System.out.println("I am printing ps " + ps.getDescription());
    }

    HashMap<String, Integer> res = new HashMap<>();

    for (int i = 0; i < pizzaSizesList.size(); i++) {
      PizzaSize currentPS = pizzaSizesList.get(i);
      int totalCaloriesForCurrentPizzaType =
          currentPS.getCaloriesPerSlice() * currentPS.getnumberOfSlices();

      if (totalCaloriesRequired <= 0) {
        break;
      }

      if (totalCaloriesRequired >= totalCaloriesForCurrentPizzaType) {
        int numberofCurrentType = (int) Math
            .floor(totalCaloriesRequired / totalCaloriesForCurrentPizzaType);
        res.put(currentPS.getDescription(), numberofCurrentType);
        totalCaloriesRequired -= numberofCurrentType * totalCaloriesForCurrentPizzaType;
      } else if (i == pizzaSizesList.size() - 1) {
        int numberofCurrentType = (int) Math
            .ceil(totalCaloriesRequired / totalCaloriesForCurrentPizzaType);
        res.put(currentPS.getDescription(), numberofCurrentType);
        totalCaloriesRequired -= numberofCurrentType * totalCaloriesForCurrentPizzaType;
      }
    }
    return new ResponseEntity<HashMap<String, Integer>>(res, HttpStatus.OK);
  }

}
