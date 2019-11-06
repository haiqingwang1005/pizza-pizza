package io.swagger.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.swagger.model.PizzaSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:/application-test.properties")
public class PizzaSizesRepositoryTest {

  @Autowired
  private PizzaSizesRepository pizzaSizesRepository;

  @Before
  public void setUp() {
    pizzaSizesRepository.deleteAll();
    PizzaSize pizzaSize = new PizzaSize().id(1L).description("small").size(11L).numberOfSlices(8)
        .caloriesPerSlice(190);
    pizzaSizesRepository.insert(pizzaSize);
  }

  @Test
  public void testSizeOnlyOne() {
    assertEquals(1, pizzaSizesRepository.findAll().size());
  }

  @Test
  public void testGetName() {
    PizzaSize pizzaSize = pizzaSizesRepository.findById(1L);
    assertEquals("small", pizzaSize.getDescription());
  }

  @Test
  public void testIsGlutenFree() {
    PizzaSize pizzaSize = pizzaSizesRepository.findById(1L);
    assertTrue(pizzaSize.getSize().equals(11L));
  }

  @Test
  public void testGetToppingType() {
    PizzaSize pizzaSize = pizzaSizesRepository.findById(1L);
    assertEquals(8, pizzaSize.getnumberOfSlices());
  }

  @Test
  public void testIsPremium() {
    PizzaSize pizzaSize = pizzaSizesRepository.findById(1L);
    assertEquals(190, pizzaSize.getCaloriesPerSlice());
  }

}
