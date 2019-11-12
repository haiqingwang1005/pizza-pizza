package io.swagger.api;


import static org.junit.Assert.assertEquals;

import io.swagger.model.CreditCard;
import io.swagger.models.Order;
import io.swagger.model.Payment;
import io.swagger.model.PaymentType;
import io.swagger.model.PriceRule;
import io.swagger.model.Receipt;
import io.swagger.repository.CreditCardsRepository;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.PriceRuleRepository;
import io.swagger.repository.ReceiptRepository;
import io.swagger.service.PriceService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:/application-test.properties")
public class GenerateReceiptApiControllerTest {

  @Autowired
  private ReceiptRepository receiptRepository;
  @Autowired
  private CreditCardsRepository creditCardsRepository;
  @Autowired
  private PriceRuleRepository priceRuleRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private PriceService privateService;
  @Autowired
  private GenerateReceiptApi api;


  @Before
  public void setUp() {
    receiptRepository.deleteAll();
    Receipt.initialize(receiptRepository);
    creditCardsRepository.deleteAll();
    CreditCard.initialize(creditCardsRepository);
    priceRuleRepository.deleteAll();
    PriceRule.initialize(priceRuleRepository);
    orderRepository.deleteAll();
    io.swagger.model.Order.initialize(orderRepository);
  }

  @Test
  public void generateValidReceipt() {
    String cardType = "American Express";
    String cardName = "Test";
    String cardNumber = "378282246310005";
    int expireMonth = 12;
    int expireYear= 2020;
    CreditCard creditCard = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);

    List<String> pizzaIds = new ArrayList<>();
    pizzaIds.add("a363c055-8601-47cc-88c7-353fb1cb66b8");
    double amount = 9.47;
    Order order = new Order().id("d6c79fbc-5e6b-48bb-bfdd-865fc34af991")
        .pizzaIds(pizzaIds).name("Fan Order");

    Payment payment = new Payment(20.00, PaymentType.CREDIT_CARD);

    ResponseEntity<Receipt> responseEntity = api.generateReceipt(creditCard, amount, order, payment);
    assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    assertEquals(1, receiptRepository.findAll().size());

  }

  @Test
  public void generateInvalidCreditCard() {
    String cardType = "Amex";
    String cardName = "Test";
    String cardNumber = "378282246310005";
    int expireMonth = 12;
    int expireYear= 2020;
    CreditCard creditCard = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);

    List<String> pizzaIds = new ArrayList<>();
    pizzaIds.add("a363c055-8601-47cc-88c7-353fb1cb66b8");
    double amount = 9.47;
    Order order = new Order().id("d6c79fbc-5e6b-48bb-bfdd-865fc34af991")
        .pizzaIds(pizzaIds).name("Fan Order");

    Payment payment = new Payment(20.00, PaymentType.CREDIT_CARD);

    ResponseEntity<Receipt> responseEntity = api.generateReceipt(creditCard, amount, order, payment);
    assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
  }

  @Test
  public void generateInvalidAmount() {
    String cardType = "American Express";
    String cardName = "Test";
    String cardNumber = "378282246310005";
    int expireMonth = 12;
    int expireYear= 2020;
    CreditCard creditCard = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);

    List<String> pizzaIds = new ArrayList<>();
    pizzaIds.add("a363c055-8601-47cc-88c7-353fb1cb66b8");
    double amount = 11.47;
    Order order = new Order().id("d6c79fbc-5e6b-48bb-bfdd-865fc34af991")
        .pizzaIds(pizzaIds).name("Fan Order");

    Payment payment = new Payment(20.00, PaymentType.CREDIT_CARD);

    ResponseEntity<Receipt> responseEntity = api.generateReceipt(creditCard, amount, order, payment);
    assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
  }
}
