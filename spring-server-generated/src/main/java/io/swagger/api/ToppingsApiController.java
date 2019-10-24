package io.swagger.api;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import io.swagger.configuration.Constants;
import io.swagger.model.Toppings;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
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

  private static final Logger log = LoggerFactory.getLogger(ToppingsApiController.class);

  private static final String COLLECTION_NAME = "toppings";

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

    FindIterable<Document> queryResult = collections.find(Filters.eq("name", name));
    if (queryResult == null) {
      log.info("SB SB cannot find the name, create one");
    } else {
      log.info(String.format("SB SB name %s already exists! override it!", name));
      collections.deleteMany(Filters.eq("name", name));
    }
    Document document = new Document();
    document.append(Toppings.NAME, name)
        .append(Toppings.IS_GLUTTEN_FREE, isGlutenFree)
        .append(Toppings.IS_PREMIUM, isPremium)
        .append(Toppings.ID, UUID.randomUUID().toString());

    collections.insertOne(document);
    log.info(
        String.format("SB SB name: %s, gluten: %b, premiun: %b", name, isGlutenFree, isPremium));
    return new ResponseEntity<Void>(HttpStatus.OK);
  }


  public ResponseEntity<List<Toppings>> searchTopping(
      @ApiParam(value = "pass an optional search string for looking up a topping") @Valid @RequestParam(value = "searchName", required = false) String searchName,
      @ApiParam(value = "pass an optional search boolean for guluten-free toppings") @Valid @RequestParam(value = "searchGlutenFree", required = false) Boolean searchGlutenFree,
      @ApiParam(value = "pass an optional search boolean for premium toppings") @Valid @RequestParam(value = "searchPremium", required = false) Boolean searchPremium) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {

      MongoClient mongoClient = MongoClients.create();
      MongoDatabase database = mongoClient.getDatabase(Constants.DB_NAME);
      MongoCollection<Document> collections = database.getCollection(COLLECTION_NAME);

      String name = searchName;
      Boolean gluten = searchGlutenFree;
      Boolean premium = searchPremium;

      List<Bson> conditionList = new ArrayList<>();
      if (name != null) {
        conditionList.add(Filters.eq(Toppings.NAME, name));
      }
      if (gluten != null) {
        conditionList.add(Filters.eq(Toppings.IS_GLUTTEN_FREE, gluten));
      }
      if (premium != null) {
        conditionList.add(Filters.eq(Toppings.IS_PREMIUM, premium));
      }

      final MongoCursor<Document> cursor;
      if (conditionList.isEmpty()) {
        cursor = collections.find().cursor();
      } else {
        cursor = collections
            .find(Filters.and(conditionList.toArray(new Bson[0]))).cursor();
      }

      List<Toppings> toppings = cursorToToppings(cursor);

      return new ResponseEntity<List<Toppings>>(toppings, HttpStatus.OK);
    }

    return new ResponseEntity<List<Toppings>>(HttpStatus.NOT_IMPLEMENTED);
  }

  private List<Toppings> cursorToToppings(MongoCursor<Document> cursor) {
    List<Toppings> res = new ArrayList<>();
    while (cursor.hasNext()) {
      Document doc = cursor.next();
      Toppings toppings = new Toppings()
          .id(UUID.fromString(doc.getString(Toppings.ID)))
          .name(doc.getString(Toppings.NAME))
          .isGlutenFree(doc.getBoolean(Toppings.IS_GLUTTEN_FREE))
          .isPremium(doc.getBoolean(Toppings.IS_PREMIUM));
      res.add(toppings);
    }
    return res;
  }
}
