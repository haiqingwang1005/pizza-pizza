package io.swagger.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.model.ToppingType;
import io.swagger.model.Toppings;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest
public class ToppingsRepositoryTest {
  @Autowired
  private ToppingsRepository repository;
  
@Before
  public void setUp() throws Exception {
    repository.deleteAll();
    
    Toppings topping = new Toppings();
    topping.name("tomato").toppingType(ToppingType.VEGETABLE).isGlutenFree(true);
    repository.insert(topping);
  }

  @After
  public void tearDown() throws Exception {
    repository.deleteAll();
  }

  @Test
  public void testOnlyOneEntry() {
    assertEquals(1, repository.count());
  }

  @Test
  public void testGetName() {
    Toppings sauce = repository.findByName("tomato");
  
    assertEquals("tomato", sauce.getName());
    assertFalse(sauce.getName().equals("bbq"));
  }

  @Test
  public void testContainsGluten() {
    Toppings sauce = repository.findByName("tomato");
  
    assertTrue(sauce.isIsGlutenFree());
  }

  @Test
  public void testToppingType() {
    Toppings sauce = repository.findByName("tomato");
  
    assertTrue(sauce.getToppingType() == ToppingType.VEGETABLE);
  }

  @Test
  public void testFindGlutenFree() {
    List<Toppings> toppings = repository.findByIsGlutenFreeIsTrue();
  
    assertEquals(1, toppings.size());
    
    for (Toppings topping : toppings) {
      assertEquals("tomato", topping.getName());
    }
  }

}