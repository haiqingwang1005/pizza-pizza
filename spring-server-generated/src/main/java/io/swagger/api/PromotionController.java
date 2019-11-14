package io.swagger.api;

import io.swagger.model.Promotion;
import io.swagger.repository.PromotionRepository;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-11-12T07:56:28.663Z[GMT]")
@Controller
public class PromotionController implements PromotionApi {

  @Autowired
  private PromotionRepository promotionRepository;

  private final HttpServletRequest request;
  private static final Logger log = LoggerFactory.getLogger(PromotionApi.class);

  @org.springframework.beans.factory.annotation.Autowired
  public PromotionController(HttpServletRequest request) {
    this.request = request;
  }

  @Override
  public ResponseEntity<Void> addPromotion(Promotion promotion) {
    String code = promotion.getCode();
    BigDecimal discount = promotion.getDiscount();
    if (code == null || code.trim().equals("") || discount.doubleValue() > 1.0) {
      return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    Promotion existingPromotion = promotionRepository.findByCode(code);
    if (existingPromotion != null) {
      log.info(String.format("Promo code %s already exists! override it!", code));
      existingPromotion.code(code);
      promotionRepository.save(existingPromotion);
      return new ResponseEntity<Void>(HttpStatus.OK);
    }

    Promotion newPromotion = new Promotion();
    newPromotion.code(code);
    newPromotion.discount(discount);
    promotionRepository.insert(newPromotion);
    log.info(String.format("Promo code: %s", code.toString()));
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> deletePromotion(String searchCode) {
    if (searchCode == null || searchCode.trim().equals("")) {
      return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
    Promotion exitingPromotion = promotionRepository.findByCode(searchCode);
    if (exitingPromotion == null) {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    promotionRepository.delete(exitingPromotion);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }


  @Override
  public ResponseEntity<Promotion> getPromotion(String code) {
    if (code == null || code.trim().equals("")) {
      return new ResponseEntity<Promotion>(HttpStatus.BAD_REQUEST);
    }
    Promotion result = promotionRepository.findByCode(code);
    if (result == null) {
      log.info(String.format("Promo code %s not found", code));
      return new ResponseEntity<Promotion>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<Promotion>(result, HttpStatus.OK);
    }
  }
}
