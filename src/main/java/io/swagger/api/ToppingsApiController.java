package io.swagger.api;

import io.swagger.model.Toppings;
import io.swagger.service.ToppingService;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToppingsApiController implements ToppingsApi {

  private static final Logger log = LoggerFactory.getLogger(ToppingsApiController.class);

  private final ToppingService toppingService;

  private final HttpServletRequest request;

  @Autowired
  public ToppingsApiController(HttpServletRequest request, ToppingService toppingService) {
    this.request = request;
    this.toppingService = toppingService;
  }

  @Override
  public ResponseEntity<Toppings> addTopping(@ApiParam(value = "Topping item to add"  )  @Valid @RequestBody Toppings body) {
    Toppings toppings = toppingService.addTopping(body);
    if (toppings == null) {
      return new ResponseEntity<Toppings>(HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<Toppings>(toppings, HttpStatus.OK);
    }
  }

  @Override
  public ResponseEntity<Toppings> deleteTopping(@NotNull @ApiParam(value = "Name of the topping that you want to delete.", required = true) @Valid @RequestParam(value = "searchName", required = true) String searchName) {
    Toppings exitingToppings = toppingService.deleteToppings(searchName);
    if (exitingToppings == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(exitingToppings, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Toppings>> searchTopping(
      @ApiParam(value = "Name for the searched topping. It is a unique value for all toppings. If the name is present, this API will return the topping with that name.") @Valid @RequestParam(value = "searchName", required = false) String searchName,
      @ApiParam(value = "If the topping is gluten free. The API will return toppings that is gluten free or not. If name is present, the API will ignore this parameter.") @Valid @RequestParam(value = "searchGlutenFree", required = false) Boolean searchGlutenFree,
      @ApiParam(value = "If the topping is premium. The API will return toppings that is premium or not. If name is present, the API will ignore this parameter.") @Valid @RequestParam(value = "searchPremium", required = false) Boolean searchPremium) {

    List<Toppings> toppings = toppingService.findToppings(searchName, searchGlutenFree, searchPremium);
    if (toppings.isEmpty()) {
      return new ResponseEntity<>(toppings, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(toppings, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<byte[]> getToppingImage(String name) {
    ClassPathResource imgFile = new ClassPathResource(String.format("image/toppings/%s.jpg", name));

    try {
      byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
      return ResponseEntity
              .ok()
              .contentType(MediaType.IMAGE_JPEG)
              .body(bytes);
    } catch (IOException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
