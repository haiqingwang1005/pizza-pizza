package io.swagger.api;

import static org.junit.Assert.*;

import io.swagger.model.Promotion;
import io.swagger.repository.PromotionRepository;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:/application-test.properties")
public class PromotionControllerTest {

  @Autowired
  private PromotionRepository promotionRepository;

  @Autowired
  private PromotionApi promotionApi;

  @Before
  public void setUp() throws Exception {
    promotionRepository.deleteAll();
    Promotion.initialize(promotionRepository);
  }

  @Test
  public void testAddPromotion() {
    Promotion promotion = new Promotion();
    promotion.code("NEW").discount(BigDecimal.valueOf(0.85));
    ResponseEntity<Void> addResponse = promotionApi.addPromotion(promotion);
    assertEquals(HttpStatus.OK, addResponse.getStatusCode());
  }

  @Test
  public void testAddExistingPromotion() {
    Promotion promotion = new Promotion();
    promotion.code("NEW").discount(BigDecimal.valueOf(0.85));
    ResponseEntity<Void> addResponse = promotionApi.addPromotion(promotion);
    assertEquals(HttpStatus.OK, addResponse.getStatusCode());
    addResponse = promotionApi.addPromotion(promotion);
    assertEquals(HttpStatus.OK, addResponse.getStatusCode());
  }

  @Test
  public void testAddInvalidPromoCode() {
    Promotion promotion = new Promotion();
    promotion.code(null).discount(BigDecimal.valueOf(0.85));
    ResponseEntity<Void> addResponse = promotionApi.addPromotion(promotion);
    assertEquals(HttpStatus.BAD_REQUEST, addResponse.getStatusCode());
    promotion.code(" ").discount(BigDecimal.valueOf(0.85));
    addResponse = promotionApi.addPromotion(promotion);
    assertEquals(HttpStatus.BAD_REQUEST, addResponse.getStatusCode());
    promotion.code("SPRING").discount(BigDecimal.valueOf(1.2));
    assertEquals(HttpStatus.BAD_REQUEST, addResponse.getStatusCode());
  }

  @Test
  public void testDeletePromotion() {
    ResponseEntity<Void> deletePromotion = promotionApi.deletePromotion("keeshond");
    assertEquals(HttpStatus.OK, deletePromotion.getStatusCode());
    deletePromotion = promotionApi.deletePromotion("DOGLOVER");
    assertEquals(HttpStatus.NOT_FOUND, deletePromotion.getStatusCode());
  }

  @Test
  public void testInvalidDeleteInput() {
    ResponseEntity<Void> deletePromotion = promotionApi.deletePromotion(null);
    assertEquals(HttpStatus.BAD_REQUEST, deletePromotion.getStatusCode());
    deletePromotion = promotionApi.deletePromotion("");
    assertEquals(HttpStatus.BAD_REQUEST, deletePromotion.getStatusCode());
  }

  @Test
  public void getPromotion() {
    ResponseEntity<Promotion> searchPromotion = promotionApi.getPromotion("keeshond");
    assertEquals(HttpStatus.OK, searchPromotion.getStatusCode());
    assertEquals(BigDecimal.valueOf(0.5), searchPromotion.getBody().getDiscount());

    searchPromotion = promotionApi.getPromotion("dogdogdog");
    assertEquals(HttpStatus.NOT_FOUND, searchPromotion.getStatusCode());
    assertNull(searchPromotion.getBody());
  }

  @Test
  public void getPromotionInvalidPromotion() {
    ResponseEntity<Promotion> searchPromotion = promotionApi.getPromotion("");
    assertEquals(HttpStatus.BAD_REQUEST, searchPromotion.getStatusCode());

    searchPromotion = promotionApi.getPromotion(null);
    assertEquals(HttpStatus.BAD_REQUEST, searchPromotion.getStatusCode());

    searchPromotion = promotionApi.getPromotion("   ");
    assertEquals(HttpStatus.BAD_REQUEST, searchPromotion.getStatusCode());
  }
}