/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.11).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-23T18:23:25.075Z[GMT]")
@Api(value = "suggestpizza", description = "the suggestpizza API")
public interface SuggestpizzaApi {

  @ApiOperation(value = "return a suggested order given the number and type of eaters.", nickname = "getOrder", notes = "Given the number and type of eaters, it provides info on how many pizzas to ordeer.  ", tags = {
      "SuggestPizzaOrder",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "The pizza number and size to order"),
      @ApiResponse(code = 405, message = "Invalid input")})
  @RequestMapping(value = "/suggestpizza",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<HashMap<String, Integer>> getOrder(
      @ApiParam(value = "the number of adult") @Valid @RequestParam(value = "Adult", required = false) Integer adult,
      @ApiParam(value = "the number of child") @Valid @RequestParam(value = "Child", required = false) Integer child);

}
