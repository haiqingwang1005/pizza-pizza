package io.swagger.api;

import io.swagger.model.Customer;
import io.swagger.model.PizzaSuggestion;
import io.swagger.service.SuggestPizzaService;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestPizzaApiController implements SuggestPizzaApi {

  private static final Logger log = LoggerFactory.getLogger(SuggestPizzaApiController.class);

  private SuggestPizzaService suggestPizzaService;

  @Autowired
  public SuggestPizzaApiController(SuggestPizzaService suggestPizzaService) {
    this.suggestPizzaService = suggestPizzaService;
  }

  @Override
  public ResponseEntity<PizzaSuggestion> getSuggestedNumberOfPizzas(
      @ApiParam(value = "the number of adult") @Valid @RequestBody Customer customer) {
    PizzaSuggestion suggestion = suggestPizzaService.getSuggestion(customer);
    return new ResponseEntity<PizzaSuggestion>(suggestion, HttpStatus.OK);
  }
}
