package io.swagger.api;

import static com.mongodb.client.model.Filters.and;

import io.swagger.configuration.Constants;
import io.swagger.model.ToppingType;
import io.swagger.model.Toppings;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repository.ToppingsRepository;

import java.util.ArrayList;
import java.util.UUID;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-22T05:48:43.671Z[GMT]")
@Controller
public class ToppingsApiController implements ToppingsApi {

  public static final String COLLECTION_NAME = "toppings";

  private static final Logger log = LoggerFactory.getLogger(ToppingsApiController.class);


  @Autowired private ToppingsRepository toppingsRepository;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public ToppingsApiController(HttpServletRequest request) {
    this.request = request;
  }

  public ResponseEntity<Void> addTopping(
      @ApiParam(value = "Topping item to add") @Valid @RequestBody Toppings body) {

    String name = body.getName();
    Boolean isGlutenFree = body.isIsGlutenFree();
    Boolean isPremium = body.isIsPremium();
    ToppingType toppingType = body.getToppingType();
    String description = body.getDescription();

    Toppings existingTopping = toppingsRepository.findByName(name);
    if (existingTopping != null) {
      log.info(String.format("SB SB name %s already exists! override it!", name));
      existingTopping.isGlutenFree(isGlutenFree).isPremium(isPremium).toppingType(toppingType).description(description);
      toppingsRepository.save(existingTopping);
      return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // have to create a new topping
    Toppings newTopping = new Toppings();
    newTopping.name(name).isGlutenFree(isGlutenFree).isPremium(isPremium).toppingType(toppingType).description(description);

    toppingsRepository.insert(newTopping);

    log.info(
        String.format("SB SB name: %s, gluten: %b, premiun: %b, toppingType: %s, description: %s",
            name, isGlutenFree, isPremium, toppingType.toString(), description));
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<Void> deleteTopping(
      @NotNull @ApiParam(value = "pass an optional search string for looking up a topping", required = true) @Valid @RequestParam(value = "searchName", required = true) String searchName,
      @ApiParam(value = "pass an optional search boolean for guluten-free toppings") @Valid @RequestParam(value = "searchGlutenFree", required = false) Boolean searchGlutenFree,
      @ApiParam(value = "pass an optional search boolean for premium toppings") @Valid @RequestParam(value = "searchPremium", required = false) Boolean searchPremium) {
    String accept = request.getHeader("Accept");

    Toppings existingTopping = toppingsRepository.findByName(searchName);
    if (existingTopping == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    toppingsRepository.delete(existingTopping);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<List<Toppings>> searchTopping(
      @ApiParam(value = "pass an optional search string for looking up a topping") @Valid @RequestParam(value = "searchName", required = false) String searchName,
      @ApiParam(value = "pass an optional search boolean for guluten-free toppings") @Valid @RequestParam(value = "searchGlutenFree", required = false) Boolean searchGlutenFree,
      @ApiParam(value = "pass an optional search boolean for premium toppings") @Valid @RequestParam(value = "searchPremium", required = false) Boolean searchPremium) {

    return new ResponseEntity<List<Toppings>>(toppingsRepository.findAll(), HttpStatus.OK);
  }

}
