package io.swagger.api;

import io.swagger.model.PizzaSize;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:/application-test.properties")
public class PizzasizesApiControllerIntegrationTest {

    @Autowired
    private PizzasizesApiController api;

    @Test
    public void getPizzaSizesTest() throws Exception {
        ResponseEntity<List<PizzaSize>> responseEntity = api.getPizzaSizes();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<PizzaSize> list = responseEntity.getBody();
        assertTrue(list.get(0).getDescription().equals("small"));
        assertTrue(list.get(1).getDescription().equals("regular"));
        assertTrue(list.get(2).getDescription().equals("large"));

    }

    @Test
    public void addPizzaSizeTest() throws Exception {
        PizzaSize body = new PizzaSize(4L,"XLarge", 20L, 15, 350);
        ResponseEntity<Void> responseEntity = api.addPizzaSize(body);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(4,api.getPizzaSizes().getBody().size());
        assertTrue(api.getPizzaSizes().getBody().get(3).getDescription().equals("XLarge"));

        PizzaSize bodyTest = new PizzaSize(1L,"XLarge", 20L, 15, 350);
        assertEquals(HttpStatus.BAD_REQUEST, api.addPizzaSize(bodyTest).getStatusCode());

        PizzaSize bodyTest2 = new PizzaSize(7L,"small", 20L, 15, 350);
        assertEquals(HttpStatus.BAD_REQUEST, api.addPizzaSize(bodyTest2).getStatusCode());

    }

    @Test
    public void deletePizzaSizeByIdTest() throws Exception {
        Long id = 4L;
        ResponseEntity<Void> responseEntity = api.deletePizzaSizeById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(3,api.getPizzaSizes().getBody().size());
        assertTrue(api.getPizzaSizes().getBody().get(0).getDescription().equals("small"));
        assertTrue(api.getPizzaSizes().getBody().get(1).getDescription().equals("regular"));
        assertTrue(api.getPizzaSizes().getBody().get(2).getDescription().equals("large"));
    }


    @Test
    public void getSizeByIdTest() throws Exception {
        Long id = 1L;
        ResponseEntity<PizzaSize> responseEntity = api.getSizeById(id);
        PizzaSize res = responseEntity.getBody();
        assertTrue(res.getDescription().equals("small"));
        assertEquals(res.getnumberOfSlices(),8);
        assertEquals(res.getCaloriesPerSlice(),190);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Long idTest = 100L;
        assertEquals(HttpStatus.NOT_FOUND,api.getSizeById(idTest).getStatusCode());

    }



}
