package io.swagger.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

public class PromotionTest {
  private Promotion promotion;

  @Before
  public void setUp() {
    promotion = new Promotion();
    promotion.discount(BigDecimal.valueOf(0.6)).code("test").id("123");
  }

  @Test
  public void testPromotion() {
    Promotion promotion2 = new Promotion();
    promotion2.setDiscount(BigDecimal.valueOf(0.6));
    promotion2.setId("123");
    promotion2.setCode("test");

    assertEquals(promotion, promotion2);
    assertEquals(promotion, promotion);
    assertNotEquals(promotion, null);
    assertNotEquals(promotion, new Object());

    assertEquals("123", promotion.getId());
    assertEquals("test", promotion.getCode());
    assertEquals(BigDecimal.valueOf(0.6), promotion.getDiscount());
  }

  @Test
  public void testHash() {
    assertNotNull(promotion.hashCode());
  }

  @Test
  public void testString() {
    assertEquals("class Promotion {\n"
        + "    code: test\n"
        + "    discount: 0.6\n"
        + "}", promotion.toString());
  }
}
