package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Pizza;
import io.swagger.service.PizzaService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PizzaApiController implements PizzaApi{

  private final PizzaService pizzaService;

  @Autowired
  public PizzaApiController(PizzaService pizzaService) {
    this.pizzaService = pizzaService;
  }

  @Override
  public ResponseEntity<Pizza> getPizzaById(@ApiParam(value = "pizza id",required = true) @PathVariable("id") String id) {
    Pizza pizza = pizzaService.getPizzaById(id);
    if (pizza == null) {
      return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Pizza> getPizzaByName(@ApiParam(value = "pizza display name", required = true) @PathVariable("name") String name) {
    Pizza pizza = pizzaService.getPizzaByName(name);
    if (pizza == null) {
      return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Pizza>> getAllPizza() {
    return new ResponseEntity<List<Pizza>>(pizzaService.getAllPizzas(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Pizza> addPizza(@ApiParam(value = "pizza to be added"  )  @Valid @RequestBody Pizza pizza) {
    Pizza result = pizzaService.addPizza(pizza);
    return new ResponseEntity<Pizza>(result, HttpStatus.OK);
  }


}