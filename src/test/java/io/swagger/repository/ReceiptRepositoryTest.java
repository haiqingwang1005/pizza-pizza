package io.swagger.repository;


import static org.junit.Assert.assertEquals;

import io.swagger.model.PaymentType;
import io.swagger.model.Toppings;
import io.swagger.models.Order;
import io.swagger.model.Receipt;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:/application-test.properties")
public class ReceiptRepositoryTest {

  @Autowired
  private ReceiptRepository receiptRepository;

  @Before
  public void setUp() {
    receiptRepository.deleteAll();
    List<String> ids = new ArrayList<>();
    ids.add("id1");
    ids.add("id2");
    Order order = new Order().id("1").pizzaIds(ids).name("name");
    Receipt receipt = new Receipt().id("1").address("address").date(LocalDate.now()).order(order).discount(15.50).paymentType(
        PaymentType.CASH);
    receiptRepository.insert(receipt);
  }

  @Test
  public void testSizeOnlyOne() {
    assertEquals(1, receiptRepository.findAll().size());
  }

  @Test
  public void testGetAddress() {
    Receipt receipt = receiptRepository.findById("1");
    assertEquals("address", receipt.getAddress());
  }

  @Test
  public void testDiscount() {
    Receipt receipt = receiptRepository.findById("1");
    assertEquals(15.50, receipt.getDiscount(),0.1);

  }

  @Test
  public void testPaymentType() {
    Receipt receipt = receiptRepository.findById("1");
    assertEquals(PaymentType.CASH, receipt.getPaymentType());

  }
}
