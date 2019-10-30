package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.models.Pizza;
import io.swagger.service.PizzaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pizza/v1")
@RestController
public class PizzaApiController implements io.swagger.apis.PizzaApi{

  @Autowired private PizzaService pizzaService;

  @Override
  public ResponseEntity<Pizza> getAPizza(@ApiParam(value = "order id",required=true) @PathVariable("id") String id) {
    return new ResponseEntity<Pizza>(pizzaService.getPizzaById(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Pizza> addPizza(@ApiParam(value = "Order to add"  )  @Valid @RequestBody Pizza pizza) {
    Pizza result = pizzaService.addPizza(pizza);
    return new ResponseEntity<Pizza>(result, HttpStatus.OK);
  }
}
