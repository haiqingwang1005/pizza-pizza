package io.swagger.api;

import io.swagger.model.Pizza;
import io.swagger.service.PizzaService;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaApiController implements PizzaApi{

  private final PizzaService pizzaService;

  @Autowired
  public PizzaApiController(PizzaService pizzaService) {
    this.pizzaService = pizzaService;
  }

  @Override
  public ResponseEntity<List<Pizza>> getPizza(@ApiParam(value = "pizza id",required = true) @RequestParam("id") String id,
                                              @ApiParam(value = "pizza display name", required = false) @RequestParam("name") String name) {
    List<Pizza> list = new ArrayList<>();
    if (!StringUtils.isEmpty(id)) {
      list.add(pizzaService.getPizzaById(id));
    } else if (!StringUtils.isEmpty(name)) {
      list.add(pizzaService.getPizzaByName(name));
    } else {
      list = pizzaService.getAllPizzas();
    }
    if (CollectionUtils.isEmpty(list)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<List<Pizza>>(list, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Pizza> addPizza(@ApiParam(value = "pizza to be added"  )  @Valid @RequestBody Pizza pizza) {
    Pizza result = pizzaService.addPizza(pizza);
    return new ResponseEntity<Pizza>(result, HttpStatus.OK);
  }


}