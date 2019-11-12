package io.swagger.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class PizzaSizeTest {
  private PizzaSize pizzaSize;
  @Before
  public void setUp() throws Exception {
    pizzaSize = new PizzaSize().id(1L).description("small").size(11L).numberOfSlices(8).caloriesPerSlice(190);
  }

  @Test
  public void initialize() {
  }

  @Test
  public void id() {
  }

  @Test
  public void getId() {
    assertEquals(1L, pizzaSize.getId().longValue());
  }

  @Test
  public void setId() {
    pizzaSize.setId(2L);
    assertEquals(2L, pizzaSize.getId().longValue());

  }

  @Test
  public void description() {
  }

  @Test
  public void getDescription() {
    assertEquals("small",pizzaSize.getDescription());
  }

  @Test
  public void setDescription() {
    pizzaSize.setDescription("SMALL");
    assertEquals("SMALL",pizzaSize.getDescription());
  }

  @Test
  public void size() {
  }

  @Test
  public void getSize() {
    assertEquals(11L, pizzaSize.getSize().longValue());
  }

  @Test
  public void setSize() {
    pizzaSize.setSize(13L);
    assertEquals(13L, pizzaSize.getSize().longValue());
  }

  @Test
  public void caloriesPerSlice() {
  }

  @Test
  public void getCaloriesPerSlice() {
    assertEquals(190,pizzaSize.getCaloriesPerSlice());
  }

  @Test
  public void setCaloriesPerSlice() {
    pizzaSize.setCaloriesPerSlice(200);
    assertEquals(200,pizzaSize.getCaloriesPerSlice());
  }

  @Test
  public void numberOfSlices() {
  }

  @Test
  public void getnumberOfSlices() {
    assertEquals(8,pizzaSize.getnumberOfSlices());
  }

  @Test
  public void setnumberOfSlices() {
    pizzaSize.setnumberOfSlices(9);
    assertEquals(9,pizzaSize.getnumberOfSlices());
  }

  @Test
  public void testEquals() {
    PizzaSize test = new PizzaSize().id(1L).description("small").size(11L).numberOfSlices(8).caloriesPerSlice(190);
    assertEquals(test, pizzaSize);
  }

  @Test
  public void testHashCode() {
    PizzaSize test = new PizzaSize().id(1L).description("small").size(11L).numberOfSlices(8).caloriesPerSlice(190);
    assertEquals(test.hashCode(),pizzaSize.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("class PizzaSize {\n"
        + "    id: 1\n"
        + "    description: small\n"
        + "    size: 11\n"
        + "}",pizzaSize.toString());
  }
}
