package io.swagger.api;

import io.swagger.model.Price;
import io.swagger.service.PriceService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceApiController implements PriceApi{

  private final PriceService priceService;

  @Autowired
  public PriceApiController(PriceService priceService) {
    this.priceService = priceService;
  }

  @Override
  public ResponseEntity<Price> getAPrice(@ApiParam(value = "item id",required=true) @RequestParam("id") String id) {
    Price price = this.priceService.getPrice(id);
    return ResponseEntity.ok(price);
  }
}
