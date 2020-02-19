package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.models.Price;
import io.swagger.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pizza/v1")
@RestController
public class PriceApiController implements io.swagger.apis.PriceApi{

  @Autowired
  private PriceService priceService;


  @Override
  public ResponseEntity<Price> getAPrice(@ApiParam(value = "item id",required=true) @PathVariable("id") String id) {
    Price price = this.priceService.getPrice(id);
    return ResponseEntity.ok(price);
  }
}
