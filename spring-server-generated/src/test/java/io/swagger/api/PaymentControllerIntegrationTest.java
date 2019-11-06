package io.swagger.api;


import static org.junit.Assert.assertEquals;

import io.swagger.model.CreditCard;
import io.swagger.repository.CreditCardsRepository;
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
public class PaymentControllerIntegrationTest {
    @Autowired
    private CreditCardsRepository creditCardsRepository;

    @Autowired
    private IsValidCreditCardApi api;


    @Before
    public void setUp() {
        creditCardsRepository.deleteAll();
        CreditCard.initialize(creditCardsRepository);
    }


    @Test
    public void testValidInput() throws Exception {
        String cardType = "American Express";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 12;
        int expireYear= 2020;
        CreditCard creditCard = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);
        ResponseEntity<Boolean> responseEntity = api.isValid(creditCard);
        assertEquals(Boolean.TRUE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testCardTypeNumberMismatch() throws Exception {
        String cardType = "Diners Club";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 12;
        int expireYear= 2020;
        CreditCard creditCard = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);
        ResponseEntity<Boolean> responseEntity = api.isValid(creditCard);
        assertEquals(Boolean.FALSE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void testMonthInvalid() throws Exception {
        String cardType = "Diners Club";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 14;
        int expireYear= 2020;
        CreditCard creditCard = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);
        ResponseEntity<Boolean> responseEntity = api.isValid(creditCard);
        assertEquals(Boolean.FALSE, responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


    @Test
    public void testYearInvalid() throws Exception {

        String cardType = "Diners Club";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 11;
        int expireYear= 2018;
        CreditCard creditCard = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);
        ResponseEntity<Boolean> responseEntity = api.isValid(creditCard);
        assertEquals(Boolean.FALSE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void testAlreadyExpired() throws Exception {

        String cardType = "Diners Club";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 1;
        int expireYear= 2019;
        CreditCard creditCard = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);
        ResponseEntity<Boolean> responseEntity = api.isValid(creditCard);
        assertEquals(Boolean.FALSE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void testInvalidCardType() throws Exception {

        String cardType = "Apple";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 1;
        int expireYear= 2020;
        CreditCard creditCard = new CreditCard(cardType,cardName,cardNumber,expireYear,expireMonth);
        ResponseEntity<Boolean> responseEntity = api.isValid(creditCard);
        assertEquals(Boolean.FALSE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
