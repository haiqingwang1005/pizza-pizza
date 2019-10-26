package io.swagger.api;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.model.CreditCard;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-25T17:23:10.744Z[GMT]")
@Controller
public class IsValidCreditCardApiController implements IsValidCreditCardApi {

  Map<String, HashSet<String>> creditCardMapping;

  private static final Logger log = LoggerFactory.getLogger(IsValidCreditCardApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public IsValidCreditCardApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
    creditCardMapping = new HashMap<>();
    creditCardMapping.put("American Express", new HashSet<String>());
    creditCardMapping.get("American Express").add("378282246310005");
    creditCardMapping.get("American Express").add("371449635398431");

    creditCardMapping.put("American Express Corporate", new HashSet<String>());
    creditCardMapping.get("American Express Corporate").add("378734493671000");

    creditCardMapping.put("Australian BankCard", new HashSet<String>());
    creditCardMapping.get("Australian BankCard").add("5610591081018250");

    creditCardMapping.put("Diners Club", new HashSet<String>());
    creditCardMapping.get("Diners Club").add("30569309025904");
    creditCardMapping.get("Diners Club").add("38520000023237");

    creditCardMapping.put("Discover", new HashSet<String>());
    creditCardMapping.get("Discover").add("6011111111111117");
    creditCardMapping.get("Discover").add("6011000990139424");

    creditCardMapping.put("JCB", new HashSet<String>());
    creditCardMapping.get("JCB").add("3530111333300000");
    creditCardMapping.get("JCB").add("3566002020360505");

    creditCardMapping.put("MasterCard", new HashSet<String>());
    creditCardMapping.get("MasterCard").add("5555555555554444");
    creditCardMapping.get("MasterCard").add("5105105105105100");

    creditCardMapping.put("Visa", new HashSet<String>());
    creditCardMapping.get("Visa").add("4111111111111111");
    creditCardMapping.get("Visa").add("4012888888881881");
    creditCardMapping.get("Visa").add("4222222222222");

    creditCardMapping.put("Dankort(PBS)", new HashSet<String>());
    creditCardMapping.get("Dankort(PBS)").add("76009244561");
    creditCardMapping.get("Dankort(PBS)").add("5019717010103742");

    creditCardMapping.put("Switch/Solo(Paymentech)", new HashSet<String>());
    creditCardMapping.get("Switch/Solo(Paymentech)").add("6331101999990016");

  }

    public ResponseEntity<Boolean> isValid(
        @ApiParam(value = "the credit card type",required=true) @Valid @RequestParam(value = "creditCardType", required = true) String creditCardType,
        @ApiParam(value = "nameOnCard",required=true) @Valid @RequestParam(value = "nameOnCard", required = true) String nameOnCard,
        @ApiParam(value = "card number",required=true) @Valid @RequestParam(value = "cardNumber", required = true) String cardNumber,
        @ApiParam(value = "expire Year",required=true) @Valid @RequestParam(value = "expireYear", required = true) int expireYear,
        @ApiParam(value = "expire Month",required=true) @Valid @RequestParam(value = "expireMonth", required = true) int expireMonth) {
    String accept = request.getHeader("Accept");

    Date d = new Date();
    int year = d.getYear();
    int month = d.getMonth();
    Boolean res = null;
    if (!creditCardMapping.containsKey(creditCardType)) {
       res = Boolean.FALSE;
      return new ResponseEntity<Boolean>(res, HttpStatus.OK);
    }

    if (creditCardMapping.containsKey(creditCardType) && !creditCardMapping.get(creditCardType)
        .contains(cardNumber)) {
       res = Boolean.FALSE;
      return new ResponseEntity<Boolean>(res, HttpStatus.OK);
    }

    if (expireMonth < 0 || expireMonth > 12
        || expireYear < year
        || expireYear == year && expireMonth < month) {
         res = Boolean.FALSE;
        return new ResponseEntity<Boolean>(res, HttpStatus.OK);
    }
    res = Boolean.TRUE;
    return new ResponseEntity<Boolean>(res, HttpStatus.OK);
  }

}
