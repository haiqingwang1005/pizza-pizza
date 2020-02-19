package io.swagger.api;

import io.swagger.model.PizzaSize;

import io.swagger.repository.PizzaSizesRepository;
import java.util.*;

import org.junit.Before;
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
public class PizzasizesControllerIntegrationTest {

    @Autowired
    private PizzaSizesRepository pizzaSizesRepository;

    @Autowired
    private PizzasizesController api;

    @Before
    public void setUp() {
        pizzaSizesRepository.deleteAll();
        PizzaSize.initialize(pizzaSizesRepository);
    }

    @Test
    public void getPizzaSizesTest() throws Exception {
        ResponseEntity<List<PizzaSize>> responseEntity = api.getPizzaSizes();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<PizzaSize> list = responseEntity.getBody();
        assertEquals(3, list.size());

    }

    @Test
    public void addPizzaSizeTest() throws Exception {
        PizzaSize pizzaSize = new PizzaSize();
        pizzaSize.id(4L).description("XLarge").size(20L).numberOfSlices(15).caloriesPerSlice(350);

        ResponseEntity<Void> responseEntity = api.addPizzaSize(pizzaSize);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(4,api.getPizzaSizes().getBody().size());
    }

    @Test
    public void addDuplicatePizzaSizeIDTest() {
        PizzaSize pizzaSize = new PizzaSize();
        pizzaSize.id(1L).description("XLarge").size(20L).numberOfSlices(15).caloriesPerSlice(350);
        ResponseEntity<Void> responseEntity = api.addPizzaSize(pizzaSize);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


    @Test
    public void deletePizzaSizeByIdTest() throws Exception {
        Long id = 3L;
        ResponseEntity<Void> responseEntity = api.deletePizzaSizeById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2,api.getPizzaSizes().getBody().size());
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
        assertEquals(HttpStatus.BAD_REQUEST,api.getSizeById(idTest).getStatusCode());

    }



}
