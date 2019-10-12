package io.swagger.api;

import io.swagger.model.PizzaSize;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryApiControllerIntegrationTest {

    @Autowired
    private PizzaSizeApi api;

    @Test
    public void addInventoryTest() throws Exception {
        PizzaSize body = new PizzaSize();
        ResponseEntity<Void> responseEntity = api.addPizzaSize(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void searchInventoryTest() throws Exception {
        Integer searchId = 1;
        ResponseEntity<List<PizzaSize>> responseEntity = api.searchPizzaSize(searchId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
