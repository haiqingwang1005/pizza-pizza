package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ToppingsTest {
  private Toppings toppings;

  @Before
  public void setUp() {
    toppings = new Toppings();
    toppings.name("name").isGlutenFree(true)
        .isPremium(true).toppingType(ToppingType.MEAT)
        .description("test").id("123");
  }

  @Test
  public void testToppings() {
    Toppings toppings2 = new Toppings();
    toppings2.setDescription("test");
    toppings2.setIsPremium(true);
    toppings2.setIsGlutenFree(true);
    toppings2.id("123");
    toppings2.setName("name");
    toppings2.setToppingType(ToppingType.MEAT);

    assertEquals(toppings2, toppings);
    assertEquals(toppings2, toppings2);
    assertNotEquals(toppings2, null);
    assertNotEquals(toppings2, new Object());

    assertEquals("test", toppings2.getDescription());
    assertEquals("name", toppings2.getName());
    assertEquals("123", toppings2.getId());
    assertEquals(ToppingType.MEAT, toppings2.getToppingType());
    assertTrue(toppings2.isIsPremium());
    assertTrue(toppings2.isIsGlutenFree());
  }

  @Test
  public void testHash() {
    assertNotNull(toppings.hashCode());
  }

  @Test
  public void testString() {
    assertEquals("class Toppings {\n"
        + "    name: name\n"
        + "    isGlutenFree: true\n"
        + "    isPremium: true\n"
        + "    toppingType: meat\n"
        + "    description: test\n"
        + "}", toppings.toString());
  }
}
