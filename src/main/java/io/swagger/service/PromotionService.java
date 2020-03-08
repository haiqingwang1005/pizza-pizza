package io.swagger.service;

import io.swagger.model.Promotion;
import io.swagger.repository.PromotionRepository;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
  public static class InvalidPromotionException extends Exception{
    public InvalidPromotionException(String message) {
      super(message);
    }
  }

  private static final Logger log = LoggerFactory.getLogger(PromotionService.class);

  private final PromotionRepository promotionRepository;

  @Autowired
  public PromotionService(PromotionRepository promotionRepository) {
    this.promotionRepository = promotionRepository;
  }

  public Promotion getPromotionFromCode(String code) {
    return this.promotionRepository.findByCode(code);
  }

  public Promotion deletePromotionForCode(String searchCode) {
    Promotion exitingPromotion = promotionRepository.findByCode(searchCode);
    if (exitingPromotion == null) {
      return null;
    }
    promotionRepository.delete(exitingPromotion);
    return exitingPromotion;
  }

  public Promotion addPromotion(Promotion promotion) throws InvalidPromotionException{
    String code = promotion.getCode();
    BigDecimal discount = promotion.getDiscount();
    if (code == null || code.trim().equals("")
        || discount.doubleValue() > 1.0 || discount.doubleValue() <= 0.0) {
      throw new InvalidPromotionException(String.format("Invalid promotion %s", promotion.toString()));
    }

    Promotion existingPromotion = promotionRepository.findByCode(code);
    if (existingPromotion != null) {
      log.info(String.format("Promo code %s already exists! override it!", code));
      existingPromotion.setCode(code);
      promotionRepository.save(existingPromotion);
      return existingPromotion;
    } else {
      Promotion newPromotion = Promotion.builder()
          .code(code)
          .discount(discount)
          .build();
      promotionRepository.insert(newPromotion);
      log.info(String.format("Promo code: %s", code.toString()));
      return newPromotion;
    }
  }
}
