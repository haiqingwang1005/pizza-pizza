package io.swagger.model;

import static org.junit.Assert.assertEquals;

import io.swagger.models.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


public class ReceiptTest {

  private Receipt receipt;
  private Receipt test;

  @Before
  public void setUp() throws Exception {
    String id = "1";
    LocalDate date = LocalDate.of(2019, 11, 11);
    String address = "7 Caraway Crescent";

    List<String> pizzaIds = new ArrayList<>();
    pizzaIds.add("a363c055-8601-47cc-88c7-353fb1cb66b8");
    Order order = new Order().id("d6c79fbc-5e6b-48bb-bfdd-865fc34af991")
        .pizzaIds(pizzaIds).name("Fan Order");

    Double totalAmount = 7.49;
    Double discount = 0.2;
    Double netAmount = 7.29;
    Double totalReceived = 10.0;
    Double change = 2.71;
    PaymentType paymentType = PaymentType.CREDIT_CARD;
    String paymentLast4Digit = "1234";
    receipt = new Receipt().id(id).date(date).address(address).order(order).totalAmount(totalAmount)
        .discount(discount).netAmount(netAmount).totalReceived(totalReceived).change(change)
        .paymentType(paymentType).paymentLast4Digit(paymentLast4Digit);

    test = new Receipt().id(id).date(date).address(address).order(order).totalAmount(totalAmount)
        .discount(discount).netAmount(netAmount).totalReceived(totalReceived).change(change)
        .paymentType(paymentType).paymentLast4Digit(paymentLast4Digit);
  }

  @Test
  public void initialize() {
  }

  @Test
  public void id() {

  }

  @Test
  public void getId() {
    assertEquals("1", receipt.getId());
  }

  @Test
  public void setId() {
    receipt.setId("2");
    assertEquals("2", receipt.getId());

  }

  @Test
  public void date() {
  }

  @Test
  public void getDate() {
    assertEquals(LocalDate.of(2019,11,11), receipt.getDate());
  }

  @Test
  public void setDate() {
    receipt.setDate(LocalDate.of(2019,11,10));
    assertEquals(LocalDate.of(2019,11,10), receipt.getDate());
  }

  @Test
  public void address() {
  }

  @Test
  public void getAddress() {
    assertEquals("7 Caraway Crescent", receipt.getAddress());
  }

  @Test
  public void setAddress() {
    receipt.setAddress("17 Caraway Crescent");
    assertEquals("17 Caraway Crescent", receipt.getAddress());
  }

  @Test
  public void order() {
  }

  @Test
  public void getOrder() {
    List<String> pizzaIds = new ArrayList<>();
    pizzaIds.add("a363c055-8601-47cc-88c7-353fb1cb66b8");
    Order test = new Order().id("d6c79fbc-5e6b-48bb-bfdd-865fc34af991")
        .pizzaIds(pizzaIds).name("Fan Order");
    assertEquals(test, receipt.getOrder());
  }

  @Test
  public void setOrder() {
    List<String> pizzaIds = new ArrayList<>();
    pizzaIds.add("a363c055-8601-47cc-88c7-353fb1cb66b8");
    Order test = new Order().id("d6c79fbc-5e6b-48bb-bfdd-865fc34af991")
        .pizzaIds(pizzaIds).name("Fan Order");
    receipt.setOrder(test);
    assertEquals(test, receipt.getOrder());
  }

  @Test
  public void totalAmount() {

  }

  @Test
  public void getTotalAmount() {
    assertEquals(7.49,receipt.getTotalAmount(),0.1);
  }

  @Test
  public void setTotalAmount() {
    receipt.setTotalAmount(100.00);
    assertEquals(100.00,receipt.getTotalAmount(),0.1);
  }

  @Test
  public void discount() {
  }

  @Test
  public void getDiscount() {
    assertEquals(0.2, receipt.getDiscount(),0.1);
  }

  @Test
  public void setDiscount() {
    receipt.setDiscount(0.3);
    assertEquals(0.3, receipt.getDiscount(),0.1);
  }

  @Test
  public void netAmount() {
  }

  @Test
  public void getNetAmount() {
    assertEquals(7.29,receipt.getNetAmount(),0.1);
  }

  @Test
  public void setNetAmount() {
    receipt.setNetAmount(7.56);
    assertEquals(7.56,receipt.getNetAmount(),0.1);
  }

  @Test
  public void totalReceived() {
  }

  @Test
  public void getTotalReceived() {
    assertEquals(10.0,receipt.getTotalReceived(),0.1);
  }

  @Test
  public void setTotalReceived() {
    receipt.setTotalReceived(20.00);
    assertEquals(20.0,receipt.getTotalReceived(),0.1);
  }

  @Test
  public void change() {

  }

  @Test
  public void getChange() {
    assertEquals(2.71,receipt.getChange(),0.1);
  }

  @Test
  public void setChange() {
    receipt.setChange(2.79);
    assertEquals(2.71,receipt.getChange(),0.1);
  }

  @Test
  public void paymentType() {
  }

  @Test
  public void getPaymentType() {
    assertEquals(PaymentType.CREDIT_CARD,receipt.getPaymentType());
  }

  @Test
  public void setPaymentType() {
    receipt.setPaymentType(PaymentType.CASH);
    assertEquals(PaymentType.CASH,receipt.getPaymentType());
  }

  @Test
  public void paymentLast4Digit() {
  }

  @Test
  public void getPaymentLast4Digit() {
    assertEquals("1234",receipt.getPaymentLast4Digit());
  }

  @Test
  public void setPaymentLast4Digit() {
    receipt.setPaymentLast4Digit("4321");
    assertEquals("4321",receipt.getPaymentLast4Digit());
  }

  @Test
  public void testEquals() {
    assertEquals(test, receipt);
  }

  @Test
  public void testHashCode() {
    assertEquals(test.hashCode(),receipt.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("class Receipt {\n"
        + "    id: 1\n"
        + "    date: 2019-11-11\n"
        + "    address: 7 Caraway Crescent\n"
        + "    order: class Order {\n"
        + "        id: d6c79fbc-5e6b-48bb-bfdd-865fc34af991\n"
        + "        pizzaIds: [a363c055-8601-47cc-88c7-353fb1cb66b8]\n"
        + "        name: Fan Order\n"
        + "    }\n"
        + "    totalAmount: 7.49\n"
        + "    discount: 0.2\n"
        + "    netAmount: 7.29\n"
        + "    totalReceived: 10.0\n"
        + "    change: 2.71\n"
        + "    paymentType: CREDIT_CARD\n"
        + "    paymentLast4Digit: 1234\n"
        + "}", receipt.toString());
  }
}
