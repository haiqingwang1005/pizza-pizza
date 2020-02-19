package io.swagger.model;

import static org.junit.Assert.assertEquals;

import io.swagger.repository.CreditCardsRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class CreditCardTest {

  private CreditCard creditCard;
  @Autowired
  private CreditCardsRepository creditCardsRepository;

  @Before
  public void setUp() throws Exception {
    String cardType = "American Express";
    String cardName = "Test";
    String cardNumber = "378282246310005";
    int expireMonth = 12;
    int expireYear= 2020;
    creditCard = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);

  }


  @Test
  public void creditCardType() {

  }

  @Test
  public void getCreditCardType() {
    assertEquals("American Express", creditCard.getCreditCardType());
  }

  @Test
  public void setCreditCardType() {
    creditCard.setCreditCardType("Amex");
    assertEquals("Amex", creditCard.getCreditCardType());
  }

  @Test
  public void nameOnCard() {
  }

  @Test
  public void getNameOnCard() {
    assertEquals("Test", creditCard.getNameOnCard());
  }

  @Test
  public void setNameOnCard() {
    creditCard.setNameOnCard("test2");
    assertEquals("test2",creditCard.getNameOnCard());
  }

  @Test
  public void cardNumber() {
  }

  @Test
  public void getCardNumber() {
    assertEquals("378282246310005", creditCard.getCardNumber());
  }

  @Test
  public void setCardNumber() {
    creditCard.setCardNumber("378282246310000");
    assertEquals("378282246310000",creditCard.getCardNumber());
  }

  @Test
  public void expireYear() {
  }

  @Test
  public void getExpireYear() {
   assertEquals(2020,creditCard.getExpireYear().intValue());
  }

  @Test
  public void setExpireYear() {
    creditCard.setExpireYear(2025);
    assertEquals(2025,creditCard.getExpireYear().intValue());

  }

  @Test
  public void expireMonth() {

  }

  @Test
  public void getExpireMonth() {
    assertEquals(12, creditCard.getExpireMonth().intValue());
  }

  @Test
  public void setExpireMonth() {
    creditCard.setExpireMonth(11);
    assertEquals(11, creditCard.getExpireMonth().intValue());
  }

  @Test
  public void testEquals() {

    String cardType = "American Express";
    String cardName = "Test";
    String cardNumber = "378282246310005";
    int expireMonth = 12;
    int expireYear= 2020;

    CreditCard test = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);
    assertEquals(test, creditCard);
  }

  @Test
  public void testHashCode() {
    String cardType = "American Express";
    String cardName = "Test";
    String cardNumber = "378282246310005";
    int expireMonth = 12;
    int expireYear= 2020;

    CreditCard test = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);
    assertEquals(test.hashCode(), creditCard.hashCode());

  }

  @Test
  public void testToString() {
    assertEquals("class CreditCard {\n"
        + "    creditCardType: American Express\n"
        + "    nameOnCard: Test\n"
        + "    cardNumber: 378282246310005\n"
        + "    expireYear: 2020\n"
        + "    expireMonth: 12\n"
        + "}",creditCard.toString());
  }
}
