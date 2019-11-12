package io.swagger.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class PaymentTest {
  private Payment payment;

  @Before
  public void setUp() throws Exception {
    payment = new Payment(20.00, PaymentType.CREDIT_CARD);
  }

  @Test
  public void getAmount() {
    assertEquals(20.00, payment.getAmount(),0.1);
  }

  @Test
  public void setAmount() {
    payment.setAmount(25.00);
    assertEquals(25.00, payment.getAmount(),0.1);
  }

  @Test
  public void getPaymentType() {
    assertEquals(PaymentType.CREDIT_CARD, payment.getPaymentType());
  }

  @Test
  public void setPaymentType() {
    payment.setPaymentType(PaymentType.CASH);
    assertEquals(PaymentType.CASH, payment.getPaymentType());
  }
}
