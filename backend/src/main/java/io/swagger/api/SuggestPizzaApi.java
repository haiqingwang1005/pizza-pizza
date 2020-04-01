package io.swagger.api;

import io.swagger.model.Customer;
import io.swagger.model.PizzaSuggestion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "suggest")
public interface SuggestPizzaApi {

  @ApiOperation(value = "Get the suggested pizza meal for customers", nickname = "getSuggestedPizzaSize", notes = "Given the number and type of eaters, it provides info on how many pizzas to order.  ", tags = {
      "Suggest Pizza Meal",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Returned successfully"),
      @ApiResponse(code = 400, message = "Invalid input，ex negative adults/children")})
  @RequestMapping(value = "/suggest",
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<PizzaSuggestion> getSuggestedNumberOfPizzas(
      @ApiParam(value = "the number of adult") @Valid @RequestBody Customer eater);

}
