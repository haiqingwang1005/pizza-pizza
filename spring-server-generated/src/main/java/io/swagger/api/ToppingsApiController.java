package io.swagger.api;

import static com.mongodb.client.model.Filters.and;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import io.swagger.configuration.Constants;
import io.swagger.model.ToppingType;
import io.swagger.model.Toppings;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.ToppingsDBConstants;
import java.util.ArrayList;
import java.util.UUID;
import org.bson.Document;
import org.bson.conversions.Bson;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-22T05:48:43.671Z[GMT]")
@Controller
public class ToppingsApiController implements ToppingsApi {

  public static final String COLLECTION_NAME = "toppings";

  private static final Logger log = LoggerFactory.getLogger(ToppingsApiController.class);


  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public ToppingsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<Void> addTopping(
      @ApiParam(value = "Topping item to add") @Valid @RequestBody Toppings body) {

    MongoClient mongoClient = MongoClients.create();

    MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
    MongoCollection<Document> collections = database.getCollection(COLLECTION_NAME);

    String name = body.getName();
    Boolean isGlutenFree = body.isIsGlutenFree();
    Boolean isPremium = body.isIsPremium();
    ToppingType toppingType = body.getToppingType();
    String description = body.getDescription();

    FindIterable<Document> queryResult = collections.find(
        and(Filters.eq(ToppingsDBConstants.NAME, name),
            Filters.eq(ToppingsDBConstants.IS_GLUTTEN_FREE, isGlutenFree),
            Filters.eq(ToppingsDBConstants.IS_PREMIUM, isPremium)));
    if (queryResult == null) {
      log.info("SB SB cannot find the topping, create one");
    } else {
      log.info(String.format("SB SB name %s already exists! override it!", name));
      collections.deleteOne(and(Filters.eq(ToppingsDBConstants.NAME, name),
          Filters.eq(ToppingsDBConstants.IS_GLUTTEN_FREE, isGlutenFree),
          Filters.eq(ToppingsDBConstants.IS_PREMIUM, isPremium)));
    }
    Document document = new Document();
    document.append(ToppingsDBConstants.NAME, name)
        .append(ToppingsDBConstants.IS_GLUTTEN_FREE, isGlutenFree)
        .append(ToppingsDBConstants.IS_PREMIUM, isPremium)
        .append(ToppingsDBConstants.ID, UUID.randomUUID().toString())
        .append(ToppingsDBConstants.TOPPING_TYPE, toppingType.toString())
        .append(ToppingsDBConstants.DESCRIPTION, description);

    collections.insertOne(document);
    log.info(
        String.format("SB SB name: %s, gluten: %b, premiun: %b, toppingType: %s, description: %s",
            name, isGlutenFree, isPremium, toppingType.toString(), description));
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<Void> deleteTopping(
      @NotNull @ApiParam(value = "pass an optional search string for looking up a topping", required = true) @Valid @RequestParam(value = "searchName", required = true) String searchName,
      @ApiParam(value = "pass an optional search boolean for guluten-free toppings") @Valid @RequestParam(value = "searchGlutenFree", required = false) Boolean searchGlutenFree,
      @ApiParam(value = "pass an optional search boolean for premium toppings") @Valid @RequestParam(value = "searchPremium", required = false) Boolean searchPremium) {
    String accept = request.getHeader("Accept");
    MongoClient mongoClient = MongoClients.create();
    MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
    MongoCollection<Document> collections = database.getCollection(COLLECTION_NAME);

    FindIterable<Document> queryResult = collections.find(
        and(Filters.eq(ToppingsDBConstants.NAME, searchName),
            Filters.eq(ToppingsDBConstants.IS_GLUTTEN_FREE, searchGlutenFree),
            Filters.eq(ToppingsDBConstants.IS_PREMIUM, searchPremium)));
    if (queryResult == null) {
      log.info("SB SB cannot find the topping, cannot delete!");
    } else {
      log.info(String.format("SB SB name %s already exists! override it!", searchName));
      collections.deleteOne(and(Filters.eq(ToppingsDBConstants.NAME, searchName),
          Filters.eq(ToppingsDBConstants.IS_GLUTTEN_FREE, searchGlutenFree),
          Filters.eq(ToppingsDBConstants.IS_PREMIUM, searchPremium)));
    }

    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<List<Toppings>> searchTopping(
      @ApiParam(value = "pass an optional search string for looking up a topping") @Valid @RequestParam(value = "searchName", required = false) String searchName,
      @ApiParam(value = "pass an optional search boolean for guluten-free toppings") @Valid @RequestParam(value = "searchGlutenFree", required = false) Boolean searchGlutenFree,
      @ApiParam(value = "pass an optional search boolean for premium toppings") @Valid @RequestParam(value = "searchPremium", required = false) Boolean searchPremium) {

    MongoClient mongoClient = MongoClients.create();
    MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
    MongoCollection<Document> collections = database.getCollection(COLLECTION_NAME);

    String name = searchName;
    Boolean gluten = searchGlutenFree;
    Boolean premium = searchPremium;

    List<Bson> conditionList = new ArrayList<>();
    if (name != null) {
      conditionList.add(Filters.eq(ToppingsDBConstants.NAME, name));
    }
    if (gluten != null) {
      conditionList.add(Filters.eq(ToppingsDBConstants.IS_GLUTTEN_FREE, gluten));
    }
    if (premium != null) {
      conditionList.add(Filters.eq(ToppingsDBConstants.IS_PREMIUM, premium));
    }

    final MongoCursor<Document> cursor;
    if (conditionList.isEmpty()) {
      cursor = collections.find().cursor();
    } else {
      cursor = collections
          .find(and(conditionList.toArray(new Bson[0]))).cursor();
    }

    List<Toppings> toppings = cursorToToppings(cursor);

    return new ResponseEntity<List<Toppings>>(toppings, HttpStatus.OK);
  }

  private List<Toppings> cursorToToppings(MongoCursor<Document> cursor) {
    List<Toppings> res = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doc = cursor.next();
      Toppings toppings = new Toppings()
          .id(UUID.fromString(doc.getString(ToppingsDBConstants.ID)))
          .name(doc.getString(ToppingsDBConstants.NAME))
          .isGlutenFree(doc.getBoolean(ToppingsDBConstants.IS_GLUTTEN_FREE))
          .isPremium(doc.getBoolean(ToppingsDBConstants.IS_PREMIUM))
          .toppingType(ToppingType.fromValue(ToppingsDBConstants.TOPPING_TYPE));
      res.add(toppings);
    }
    return res;
  }
}
