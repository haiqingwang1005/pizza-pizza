package io.swagger.api;

import static org.junit.Assert.assertEquals;

import io.swagger.model.Apetite;
import io.swagger.model.Calories;
import io.swagger.model.Eater;
import io.swagger.model.PizzaSize;
import io.swagger.model.SuggestedPizzaSizeOrder;
import io.swagger.repository.CaloriesRepository;
import io.swagger.repository.PizzaSizesRepository;
import java.util.HashMap;
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
public class SuggestpizzaControllerIntegrationTest {

  @Autowired
  private CaloriesRepository caloriesRepository;

  @Autowired
  private PizzaSizesRepository pizzaSizesRepository;

  @Autowired
  private Suggestpizza api;

  @Before
  public void setUp() {
    caloriesRepository.deleteAll();
    Calories.initialize(caloriesRepository);

    pizzaSizesRepository.deleteAll();
    PizzaSize.initialize(pizzaSizesRepository);
  }

  @Test
  public void getOrderTest() throws Exception {

    ResponseEntity<SuggestedPizzaSizeOrder> responseEntity = api
        .getOrder(new Eater( 4,  2));
    HashMap<String, Integer> pizzaSizeNumber = responseEntity.getBody().getPizzaSizeSuggestion();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(2, pizzaSizeNumber.size());
    assertEquals(1, pizzaSizeNumber.get("regular").intValue());
    assertEquals(1, pizzaSizeNumber.get("small").intValue());

  }


  @Test
  public void getOrderTest1() throws Exception {
    ResponseEntity<SuggestedPizzaSizeOrder> responseEntity = api
        .getOrder(new Eater( 6,  1));
    HashMap<String, Integer> pizzaSizeNumber = responseEntity.getBody().getPizzaSizeSuggestion();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(1, pizzaSizeNumber.get("large").intValue());
    assertEquals(1, pizzaSizeNumber.get("small").intValue());
  }


  @Test
  public void getOrderTest2() throws Exception {
    ResponseEntity<SuggestedPizzaSizeOrder> responseEntity = api
        .getOrder(new Eater(2,  0));
    HashMap<String, Integer> pizzaSizeNumber = responseEntity.getBody().getPizzaSizeSuggestion();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(1, pizzaSizeNumber.get("small").intValue());
  }

}
