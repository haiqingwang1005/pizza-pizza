package io.swagger.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.swagger.model.ToppingType;
import io.swagger.model.Toppings;
import java.util.List;

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
public class ToppingsRepositoryTest {

  @Autowired
  private ToppingsRepository toppingsRepository;

  @Before
  public void setUp() {
    toppingsRepository.deleteAll();
    Toppings toppings = new Toppings().name("tomato")
        .toppingType(ToppingType.VEGETABLE)
        .isPremium(true)
        .isGlutenFree(false);
    toppingsRepository.insert(toppings);
  }

  @Test
  public void testSizeOnlyOne() {
    assertEquals(1, toppingsRepository.findAll().size());
  }

  @Test
  public void testGetName() {
    Toppings toppings = toppingsRepository.findByName("tomato");
    assertEquals("tomato", toppings.getName());
  }

  @Test
  public void testIsGlutenFree() {
    Toppings toppings = toppingsRepository.findByName("tomato");
    assertFalse(toppings.isIsGlutenFree());
  }

  @Test
  public void testGetToppingType() {
    Toppings toppings = toppingsRepository.findByName("tomato");
    assertEquals(ToppingType.VEGETABLE,
        toppings.getToppingType());
  }

  @Test
  public void testIsPremium() {
    Toppings toppings = toppingsRepository.findByName("tomato");
    assertTrue(toppings.isIsPremium());
  }

  @Test
  public void testFindGlutenFree() {
    List<Toppings> toppings = toppingsRepository.findByIsGlutenFree(false);
    assertEquals(1, toppings.size());
    assertEquals("tomato", toppings.get(0).getName());
  }
}
