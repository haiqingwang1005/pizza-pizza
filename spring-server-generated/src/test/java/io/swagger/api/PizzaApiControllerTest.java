package io.swagger.api;

import static org.junit.Assert.assertEquals;

import io.swagger.models.Base;
import io.swagger.models.Pizza;
import io.swagger.models.PizzaSize;
import io.swagger.models.ToppingType;
import io.swagger.models.Toppings;
import io.swagger.models.Type;
import io.swagger.models.CrustType;
import io.swagger.models.SauceType;
import io.swagger.repository.PizzaRepository;
import io.swagger.service.PizzaService;
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
public class PizzaApiControllerTest {

  @Autowired
  private PizzaRepository pizzaRepository;

  @Autowired
  private PizzaApiController pizzaApiController;

  @Before
  public void setUp() {
    pizzaRepository.deleteAll();
    io.swagger.model.Pizza.initialize(pizzaRepository);
  }

  @Test
  public void testAddPizza() {
    List<Toppings> defaultToppigns = new ArrayList<>();
    defaultToppigns.add(new Toppings().name("pepperoni").isGlutenFree(true)
        .isPremium(false).toppingType(ToppingType.MEAT)
        .description("Thid is test pepperoni topping."));
    PizzaSize pizzaSize = new PizzaSize().id(1L).description("small").size(11L).noOfSlices(8).caloriesPerSlice(190);
    Base base = new Base().crustType(CrustType.ORIGINAL_STUFFED_CRUST).sauceType(SauceType.BARBEQUE).pizzaSize(pizzaSize);
    Pizza pizza = new Pizza().toppings(defaultToppigns).type(Type.BUILDYOUROWN).base(base).ownerId("Fan").isGlutenFree(false).displayName("Fan P");
    ResponseEntity<io.swagger.models.Pizza> addResponse = pizzaApiController.addPizza(pizza);
    assertEquals(HttpStatus.OK, addResponse.getStatusCode());
  }


  @Test
  public void getPizza() {
    ResponseEntity<io.swagger.models.Pizza> searchPizza = pizzaApiController.getAPizza("a363c055-8601-47cc-88c7-353fb1cb66b8");
    assertEquals(HttpStatus.OK, searchPizza.getStatusCode());
    assertEquals("Fan Fan", searchPizza.getBody().getOwnerId());
  }
}