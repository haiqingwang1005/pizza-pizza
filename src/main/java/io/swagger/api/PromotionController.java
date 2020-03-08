package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Promotion;
import io.swagger.service.PromotionService;
import io.swagger.service.PromotionService.InvalidPromotionException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromotionController implements PromotionApi {

  private final PromotionService promotionService;

  private final HttpServletRequest request;

  private static final Logger log = LoggerFactory.getLogger(PromotionApi.class);

  @Autowired
  public PromotionController(HttpServletRequest request, PromotionService promotionService) {
    this.request = request;
    this.promotionService = promotionService;
  }

  @Override
  public ResponseEntity<Promotion> addPromotion(@ApiParam(value = "promotion code item to add"  )  @Valid @RequestBody Promotion promotion) {
    try {
      Promotion addedPromotion = this.promotionService.addPromotion(promotion);
      return new ResponseEntity<Promotion>(addedPromotion, HttpStatus.OK);
    } catch (InvalidPromotionException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<Promotion> deletePromotion(@NotNull @ApiParam(value = "pass a promotion code string for looking up a promotion", required = true) @Valid @RequestParam(value = "searchCode", required = true) String searchCode) {
    if (searchCode == null || searchCode.trim().equals("")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Promotion promotion = promotionService.deletePromotionForCode(searchCode);
    if (promotion == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(promotion, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Promotion> getPromotion(@NotNull @ApiParam(value = "promotion code to use.", required = true) @Valid @RequestParam(value = "code", required = true) String code) {
    if (code == null || code.trim().equals("")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Promotion result = promotionService.getPromotionFromCode(code);
    if (result == null) {
      log.info(String.format("Promo code %s not found", code));
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(result, HttpStatus.OK);
    }
  }
}
