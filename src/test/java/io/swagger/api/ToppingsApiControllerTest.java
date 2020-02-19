package io.swagger.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.swagger.model.ToppingType;
import io.swagger.model.Toppings;
import io.swagger.repository.ToppingsRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class ToppingsApiControllerTest {

  @Autowired
  private ToppingsRepository toppingsRepository;

  @Autowired
  private ToppingsApi api;

  @Before
  public void setUp() {
    toppingsRepository.deleteAll();
    Toppings.initialize(toppingsRepository);
  }

  @Test
  public void testSearchToppingAll() {
    ResponseEntity<List<Toppings>> responseEntity = api.searchTopping(null, null, null);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    List<Toppings> toppingsList = responseEntity.getBody();

    assertEquals(6, toppingsList.size());
  }

  @Test
  public void testSearchToppingByName() {
    ResponseEntity<List<Toppings>> responseEntity = api.searchTopping("pepperoni", null, null);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    List<Toppings> toppingsList = responseEntity.getBody();

    assertEquals(1, toppingsList.size());
    assertEquals("pepperoni", toppingsList.get(0).getName());
    assertTrue(toppingsList.get(0).isIsGlutenFree());
    assertFalse(toppingsList.get(0).isIsPremium());
    assertEquals(ToppingType.MEAT, toppingsList.get(0).getToppingType());
  }

  @Test
  public void testSearchToppingByNameNotFound() {
    ResponseEntity<List<Toppings>> responseEntity = api.searchTopping("invalid_name", null, null);
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    List<Toppings> toppingsList = responseEntity.getBody();

    assertTrue(toppingsList.isEmpty());
  }
  @Test
  public void testSearchToppingByGlutenPremium() {
    ResponseEntity<List<Toppings>> responseEntity = api.searchTopping(null, true, false);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    List<Toppings> toppingsList = responseEntity.getBody();

    assertEquals(3, toppingsList.size());

    Set<String> set = new HashSet<>();
    set.add(toppingsList.get(0).getName());
    set.add(toppingsList.get(1).getName());
    set.add(toppingsList.get(2).getName());

    assertTrue(set.contains("pepperoni"));
    assertTrue(set.contains("sausage"));
    assertTrue(set.contains("onions"));
  }

  @Test
  public void testDeleteToppings() {
    ResponseEntity<Void> deleteResponse = api.deleteTopping("pepperoni");
    assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());

    ResponseEntity<List<Toppings>> searchResponse = api.searchTopping("pepperoni", null, null);
    assertEquals(HttpStatus.NOT_FOUND, searchResponse.getStatusCode());
  }

  @Test
  public void testDeleteToppingsNotFound() {
    ResponseEntity<Void> deleteResponse = api.deleteTopping("invalid");
    assertEquals(HttpStatus.NOT_FOUND, deleteResponse.getStatusCode());
  }

  @Test
  public void testAddToppings() {
    Toppings toppings = new Toppings();
    toppings.name("cheese").isPremium(true).isGlutenFree(true).toppingType(ToppingType.VEGETABLE);
    ResponseEntity<Void> addResponse = api.addTopping(toppings);
    assertEquals(HttpStatus.OK, addResponse.getStatusCode());

    ResponseEntity<List<Toppings>> searchResponse = api.searchTopping("cheese", null, null);
    assertEquals(HttpStatus.OK, searchResponse.getStatusCode());
  }

  @Test
  public void testAddToppingsExisting() {
    Toppings toppings = new Toppings();
    toppings.name("pepperoni").isPremium(true).isGlutenFree(true).toppingType(ToppingType.VEGETABLE);
    ResponseEntity<Void> addResponse = api.addTopping(toppings);
    assertEquals(HttpStatus.OK, addResponse.getStatusCode());

    ResponseEntity<List<Toppings>> searchResponse = api.searchTopping("pepperoni", null, null);
    assertEquals(HttpStatus.OK, searchResponse.getStatusCode());

    List<Toppings> toppingsList = searchResponse.getBody();

    assertEquals(1, toppingsList.size());
    assertEquals("pepperoni", toppingsList.get(0).getName());
    assertTrue(toppingsList.get(0).isIsGlutenFree());
    assertTrue(toppingsList.get(0).isIsPremium());
    assertEquals(ToppingType.VEGETABLE, toppingsList.get(0).getToppingType());
  }
}
