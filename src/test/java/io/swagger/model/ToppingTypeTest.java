package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ToppingTypeTest {
  @Test
  public void testFromValue() {
    assertEquals(ToppingType.MEAT, ToppingType.fromValue("meat"));
    assertEquals(ToppingType.VEGETABLE, ToppingType.fromValue("vegetable"));
    assertNull(ToppingType.fromValue("invalid"));
  }
}
