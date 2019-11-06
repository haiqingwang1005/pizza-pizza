package io.swagger.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.CreditCard;
import io.swagger.repository.CreditCardsRepository;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-25T17:23:10.744Z[GMT]")
@Controller
public class PaymentController implements IsValidCreditCardApi {

  private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;
  public static final int TOTAL_NUMBER_OF_MONTHS_IN_YEAR = 12;
  public static final int ZERO = 0;

  @Autowired
  private CreditCardsRepository creditCardsRepository;

  @org.springframework.beans.factory.annotation.Autowired
  public PaymentController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;

  }

  public ResponseEntity<Boolean> isValid(
      @ApiParam(value = "the credit card", required = true) @Valid @RequestBody CreditCard creditCard)
       {
    String accept = request.getHeader("Accept");
    Date d = new Date();
    int year = d.getYear();
    int month = d.getMonth();
    Boolean res = null;

    int expireMonth = creditCard.getExpireMonth();
    int expireYear = creditCard.getExpireYear();
    String cardNumber = creditCard.getCardNumber();
    String creditCardType = creditCard.getCreditCardType();

    if (expireMonth < ZERO || expireMonth > TOTAL_NUMBER_OF_MONTHS_IN_YEAR
        || expireYear < year
        || expireYear == year && expireMonth < month) {
      res = Boolean.FALSE;
      return new ResponseEntity<Boolean>(res, HttpStatus.BAD_REQUEST);
    }

    CreditCard target = creditCardsRepository.findByCardNumber(cardNumber);
    if(target == null) {
      res = Boolean.FALSE;
      return new ResponseEntity<Boolean>(res, HttpStatus.OK);
    }

    if (!target.getCreditCardType().equals(creditCardType)) {
      res = Boolean.FALSE;
      return new ResponseEntity<Boolean>(res, HttpStatus.OK);
    }

    res = Boolean.TRUE;
    return new ResponseEntity<Boolean>(res, HttpStatus.OK);
  }

}
