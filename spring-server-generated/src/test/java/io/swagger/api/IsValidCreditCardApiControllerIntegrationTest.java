package io.swagger.api;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.model.CreditCard;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsValidCreditCardApiControllerIntegrationTest {

    @Autowired
    private IsValidCreditCardApi api;

    @Test
    public void isValidTest() throws Exception {
        CreditCard creditCard = new CreditCard();
        String cardType = "American Express";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 12;
        int expireYear= 2020;
        ResponseEntity<Boolean> responseEntity = api.isValid(cardType,cardName,cardNumber,expireYear,expireMonth);
        assertEquals(Boolean.TRUE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void isCardTypeNumberMismatch() throws Exception {
        CreditCard creditCard = new CreditCard();
        String cardType = "Diners Club";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 12;
        int expireYear= 2020;
        ResponseEntity<Boolean> responseEntity = api.isValid(cardType,cardName,cardNumber,expireYear,expireMonth);
        assertEquals(Boolean.FALSE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void isMonthInvalid() throws Exception {
        CreditCard creditCard = new CreditCard();
        String cardType = "Diners Club";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 14;
        int expireYear= 2020;
        ResponseEntity<Boolean> responseEntity = api.isValid(cardType,cardName,cardNumber,expireYear,expireMonth);
        assertEquals(Boolean.FALSE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void isYearInvalid() throws Exception {
        CreditCard creditCard = new CreditCard();
        String cardType = "Diners Club";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 11;
        int expireYear= 2018;
        ResponseEntity<Boolean> responseEntity = api.isValid(cardType,cardName,cardNumber,expireYear,expireMonth);
        assertEquals(Boolean.FALSE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void isAlreadyExpired() throws Exception {
        CreditCard creditCard = new CreditCard();
        String cardType = "Diners Club";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 1;
        int expireYear= 2019;
        ResponseEntity<Boolean> responseEntity = api.isValid(cardType,cardName,cardNumber,expireYear,expireMonth);
        assertEquals(Boolean.FALSE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void isInvalidCardType() throws Exception {
        CreditCard creditCard = new CreditCard();
        String cardType = "Apple";
        String cardName = "Test";
        String cardNumber = "378282246310005";
        int expireMonth = 1;
        int expireYear= 2020;
        ResponseEntity<Boolean> responseEntity = api.isValid(cardType,cardName,cardNumber,expireYear,expireMonth);
        assertEquals(Boolean.FALSE, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
