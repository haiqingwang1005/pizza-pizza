package io.swagger.api;

import static org.junit.Assert.assertEquals;

import io.swagger.models.Base;
import io.swagger.models.CrustType;
import io.swagger.models.Pizza;
import io.swagger.models.PizzaSize;
import io.swagger.models.Price;
import io.swagger.models.SauceType;
import io.swagger.models.ToppingType;
import io.swagger.models.Toppings;
import io.swagger.models.Type;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.ToppingsRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:/application-test.properties")
public class PriceApiControllerTest {

  @Autowired
  private PizzaRepository pizzaRepository;

  @Autowired
  private ToppingsRepository toppingsRepository;

  @Autowired
  private PriceApiController priceApiController;

  @Before
  public void setUp() {
    toppingsRepository.deleteAll();
    io.swagger.model.Toppings.initialize(toppingsRepository);
    pizzaRepository.deleteAll();
    io.swagger.model.Pizza.initialize(pizzaRepository);
  }

  @Test
  public void getPrice() {
    ResponseEntity<Price> getPrice = priceApiController.getAPrice("a363c055-8601-47cc-88c7-353fb1cb66b8");
    assertEquals(HttpStatus.OK, getPrice.getStatusCode());
    assertEquals(new Double(9.47), getPrice.getBody().getPrice());
  }
}