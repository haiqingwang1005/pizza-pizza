package io.swagger.repository;

import static org.junit.Assert.assertEquals;

import io.swagger.model.CreditCard;
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
public class CreditCardsRepositoryTest {

  @Autowired
  private CreditCardsRepository creditCardsRepository;

  @Before
  public void setUp() {
    creditCardsRepository.deleteAll();
    CreditCard creditCard = new CreditCard().nameOnCard("Test").creditCardType("MasterCard")
        .cardNumber("1111111111").expireMonth(11).expireYear(2020);
    creditCardsRepository.insert(creditCard);
  }

  @Test
  public void testSizeOnlyOne() {
    assertEquals(1, creditCardsRepository.findAll().size());
  }

  @Test
  public void testGetName() {
    CreditCard creditCard = creditCardsRepository.findByCardNumber("1111111111");
    assertEquals("MasterCard", creditCard.getCreditCardType());
  }

  @Test
  public void testIsGlutenFree() {
    CreditCard creditCard = creditCardsRepository.findByCardNumber("1111111111");
    assertEquals("Test", creditCard.getNameOnCard());
  }

  @Test
  public void testGetToppingType() {
    CreditCard creditCard = creditCardsRepository.findByCardNumber("1111111111");
    assertEquals(new Integer(11), creditCard.getExpireMonth());
  }

  @Test
  public void testIsPremium() {
    CreditCard creditCard = creditCardsRepository.findByCardNumber("1111111111");
    assertEquals(new Integer(2020), creditCard.getExpireYear());
  }

}
