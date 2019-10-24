package io.swagger.api;

import io.swagger.model.ArrayOfPizzaSizes;
import io.swagger.model.PizzaSize;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-12T05:53:23.679Z[GMT]")
@Controller
public class PizzasizesApiController implements PizzasizesApi {

    private static final Logger log = LoggerFactory.getLogger(PizzasizesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PizzasizesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addPizzaSize(@ApiParam(value = ""  )  @Valid @RequestBody PizzaSize body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<ArrayOfPizzaSizes> getPizzaSizes() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<ArrayOfPizzaSizes>(objectMapper.readValue("[ {\n  \"size\" : 7,\n  \"description\" : \"small\",\n  \"id\" : 1\n}, {\n  \"size\" : 7,\n  \"description\" : \"small\",\n  \"id\" : 1\n} ]", ArrayOfPizzaSizes.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ArrayOfPizzaSizes>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ArrayOfPizzaSizes>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PizzaSize> getSizeById(@ApiParam(value = "id of pizza size to return",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PizzaSize>(objectMapper.readValue("{\n  \"size\" : 7,\n  \"description\" : \"small\",\n  \"id\" : 1\n}", PizzaSize.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PizzaSize>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PizzaSize>(HttpStatus.NOT_IMPLEMENTED);
    }

}