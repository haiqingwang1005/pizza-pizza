package io.swagger.api;

import io.swagger.model.ToppingType;
import io.swagger.model.Toppings;
import io.swagger.annotations.*;
import io.swagger.repository.ToppingsRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-22T05:48:43.671Z[GMT]")
@Controller
public class ToppingsApiController implements ToppingsApi {

  private static final Logger log = LoggerFactory.getLogger(ToppingsApiController.class);

  @Autowired
  private ToppingsRepository toppingsRepository;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public ToppingsApiController(HttpServletRequest request) {
    this.request = request;
  }

  @Override
  public ResponseEntity<Void> addTopping(
      @ApiParam(value = "Topping item to add") @Valid @RequestBody Toppings body) {

    String name = body.getName();
    Boolean isGlutenFree = body.isIsGlutenFree();
    Boolean isPremium = body.isIsPremium();
    ToppingType toppingType = body.getToppingType();
    String description = body.getDescription();

    if (name == null || name.trim().equals("")) {
      return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    Toppings existingTopping = toppingsRepository.findByName(name);
    if (existingTopping != null) {
      log.info(String.format("PizzaPizza Toppings name %s already exists! override it!", name));
      existingTopping.isGlutenFree(isGlutenFree)
          .isPremium(isPremium)
          .toppingType(toppingType)
          .description(description);
      toppingsRepository.save(existingTopping);
      return new ResponseEntity<Void>(HttpStatus.OK);
    }

    Toppings newTopping = new Toppings();
    newTopping.name(name).isGlutenFree(isGlutenFree)
        .isPremium(isPremium)
        .toppingType(toppingType)
        .description(description);
    toppingsRepository.insert(newTopping);

    log.info(
        String.format("name: %s, gluten: %b, premiun: %b, toppingType: %s, description: %s",
            name, isGlutenFree, isPremium, toppingType.toString(), description));
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> deleteTopping(
      @NotNull @ApiParam(value = "pass an optional search string for looking up a topping", required = true) @Valid @RequestParam(value = "searchName", required = true) String searchName) {
    if (searchName == null) {
      return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
    Toppings exitingToppings = toppingsRepository.findByName(searchName);
    if (exitingToppings == null) {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    toppingsRepository.delete(exitingToppings);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Toppings>> searchTopping(
      @ApiParam(value = "pass an optional search string for looking up a topping") @Valid @RequestParam(value = "searchName", required = false) String searchName,
      @ApiParam(value = "pass an optional search boolean for guluten-free toppings") @Valid @RequestParam(value = "searchGlutenFree", required = false) Boolean searchGlutenFree,
      @ApiParam(value = "pass an optional search boolean for premium toppings") @Valid @RequestParam(value = "searchPremium", required = false) Boolean searchPremium) {

    List<Toppings> toppings = new ArrayList<>();
    if (searchName != null) {
      Toppings result = toppingsRepository.findByName(searchName);
      if (result != null) {
        toppings.add(result);
      }
    } else {
      Set<Toppings> set = new HashSet<>();
      set.addAll(toppingsRepository.findAll());
      if (searchGlutenFree != null) {
        set.retainAll(toppingsRepository.findByIsGlutenFree(searchGlutenFree));
      }
      if (searchPremium != null) {
        set.retainAll(toppingsRepository.findByIsPremium(searchPremium));
      }
      toppings.addAll(set);
    }
    if (toppings.isEmpty()) {
      return new ResponseEntity<List<Toppings>>(toppings, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Toppings>>(toppings, HttpStatus.OK);
  }
}
