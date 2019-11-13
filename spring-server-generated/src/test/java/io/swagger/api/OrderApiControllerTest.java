package io.swagger.api;

import static org.junit.Assert.assertEquals;

import io.swagger.models.Order;
import io.swagger.models.Base;
import io.swagger.models.CrustType;
import io.swagger.models.Pizza;
import io.swagger.models.PizzaSize;
import io.swagger.models.SauceType;
import io.swagger.models.ToppingType;
import io.swagger.models.Toppings;
import io.swagger.models.Type;
import io.swagger.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
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
public class OrderApiControllerTest {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderApiController orderApiController;

  @Before
  public void setUp() {
    orderRepository.deleteAll();
    io.swagger.model.Order.initialize(orderRepository);
  }

  @Test
  public void testMakeOrder() {
    List<String> pizzaIds = new ArrayList<>();
    pizzaIds.add("a363c055-8601-47cc-88c7-353fb1cb66b8");
    Order order = new Order().id("d6c79fbc-5e6b-48bb-bfdd-865fc34af992").pizzaIds(pizzaIds).name("Fan Order");
    ResponseEntity<Order> addResponse = orderApiController.makeOrder(order);
    assertEquals(HttpStatus.OK, addResponse.getStatusCode());
  }


  @Test
  public void getOrder() {
    ResponseEntity<Order> searchOrder = orderApiController.getAOrder("d6c79fbc-5e6b-48bb-bfdd-865fc34af991");
    assertEquals(HttpStatus.OK, searchOrder.getStatusCode());
    assertEquals("Fan Order", searchOrder.getBody().getName());
  }
}