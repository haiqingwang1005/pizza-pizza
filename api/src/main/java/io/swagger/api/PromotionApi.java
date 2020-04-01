package io.swagger.api;

import io.swagger.model.Promotion;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Api(value = "promotion")
public interface PromotionApi {

  @ApiOperation(value = "add a valid promotion code with corresponding discount in database.",
      nickname = "addPromotion",
      notes = "Add a new promotion code with the percentage of original price. If the promotion code already exist,"
          + "override it. The price percentage means customer only need to pay the discount percentage of original price.", tags={ "Promotion Operation", })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "discount for promotion code created. or an existing item already exist, override it"),
      @ApiResponse(code = 400, message = "invalid input, ex: empty code or discount, or the discount is larger than 1.0"),})
  @RequestMapping(value = "/promotion",
      consumes = { "application/json" },
      method = RequestMethod.POST)
  ResponseEntity<Promotion> addPromotion(@ApiParam(value = "promotion code item to add"  )  @Valid @RequestBody Promotion body);


  @ApiOperation(value = "Deletes a promotion code from the system",
      nickname = "deletePromotion",
      notes = "Delete a promotion code in the system", tags={ "Promotion Operation", })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "item deleted"),
      @ApiResponse(code = 400, message = "invalid input, object invalid"),
      @ApiResponse(code = 404, message = "an existing item doesn't exist") })
  @RequestMapping(value = "/promotion",
      method = RequestMethod.DELETE)
  ResponseEntity<Promotion> deletePromotion(@NotNull @ApiParam(value = "pass a promotion code string for looking up a promotion", required = true) @Valid @RequestParam(value = "searchCode", required = true) String searchCode);


  @ApiOperation(value = "Take in an promotion code and return the percentage of the final price. \n"
      + "For example, passing a code \"keeshond\" will get back 0.6, which means the order price will be 60% of the original.",
      nickname = "getPromotion",
      notes = "Get discount by promotion code ",
      response = Promotion.class, tags={ "Promotion Operation", })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "discount after discount is successfully returned.", response = Promotion.class),
      @ApiResponse(code = 400, message = "invalid input, object invalid"),
      @ApiResponse(code = 404, message = "invalid input, object invalid")})
  @RequestMapping(value = "/promotion",
      produces = { "application/json" },
      method = RequestMethod.GET)
  ResponseEntity<Promotion> getPromotion(@NotNull @ApiParam(value = "promotion code to use.", required = true) @Valid @RequestParam(value = "code", required = true) String code);

}
