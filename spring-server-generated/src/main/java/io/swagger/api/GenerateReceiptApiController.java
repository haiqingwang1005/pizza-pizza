package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.CreditCard;
import io.swagger.model.Payment;
import io.swagger.model.Receipt;
import io.swagger.models.Price;
import io.swagger.repository.ReceiptRepository;
import io.swagger.service.PriceService;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-12T01:52:56.484Z[GMT]")
@Controller
public class GenerateReceiptApiController implements GenerateReceiptApi {

  private static final Logger log = LoggerFactory.getLogger(GenerateReceiptApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired
  private PaymentController paymentController;

  @Autowired
  private PriceService priceService;

  @Autowired
  private StoresApiController storesApiController;

  @Autowired
  private ReceiptRepository receiptRepository;

  @org.springframework.beans.factory.annotation.Autowired
  public GenerateReceiptApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Receipt> generateReceipt(
      @ApiParam(value = "credit card", required = true) @Valid @RequestBody CreditCard creditCard,
      @ApiParam(value = "amount expected to pay", required = true) @Valid @RequestBody Double amount,
      @ApiParam(value = "order", required = true) @Valid @RequestBody io.swagger.models.Order order,
      @ApiParam(value = "payment from customer", required = true) @Valid @RequestBody Payment payment) {
    String accept = request.getHeader("Accept");

    Receipt receipt = null;
    ResponseEntity<Boolean> validateCreditCard = paymentController.isValid(creditCard);
    if(validateCreditCard.getBody().equals(Boolean.FALSE)) {
      log.info(String.format("Transaction failed! Your card ending with %s is not accepted. ", getLast4Digit(creditCard)));
      return new ResponseEntity<Receipt>(receipt,HttpStatus.BAD_REQUEST);
    }
    double totalOrderAmount = 0.0;
    List<String> ids = order.getPizzaIds();
    for(String id: ids) {
      totalOrderAmount += priceService.getPrice(id).getPrice();
    }

    if(totalOrderAmount != amount) {
      log.info(String.format("Transaction failed! The price of order has been changed."));
      return new ResponseEntity<Receipt>(receipt,HttpStatus.BAD_REQUEST);
    }

    receipt = new Receipt();
    receipt.setAddress(storesApiController.PIZZA_STORE_1_NAME);
    receipt.setDate(LocalDate.now());
    receipt.setOrder(order);
    receipt.setTotalAmount(amount);
    receipt.setDiscount(0.0);
    receipt.setNetAmount(receipt.getTotalAmount()-receipt.getDiscount());
    receipt.setTotalReceived(payment.getAmount());
    receipt.setChange(payment.getAmount()-amount);
    receipt.setPaymentType(payment.getPaymentType());
    if(payment.getPaymentType().equals("CREDIT_CARD")) {
      receipt.setPaymentLast4Digit(getLast4Digit(creditCard));
    }

    receiptRepository.insert(receipt);
    return new ResponseEntity<Receipt>(receipt,HttpStatus.OK);
  }


  private String getLast4Digit(CreditCard creditCard) {
    String creditCardNumber = creditCard.getCardNumber();
    int totalLen = creditCardNumber.length();
    int startIndex = totalLen-4;
    return creditCardNumber.substring(startIndex);

  }

}
