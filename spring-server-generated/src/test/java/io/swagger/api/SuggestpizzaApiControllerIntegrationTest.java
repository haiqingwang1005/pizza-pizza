package io.swagger.api;

import java.util.HashMap;
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
public class SuggestpizzaApiControllerIntegrationTest {

    @Autowired
    private SuggestpizzaApi api;

    @Test
    public void getOrderTest() throws Exception {

        ResponseEntity<HashMap<String, Integer>> responseEntity = api.getOrder(4,2);
        HashMap<String, Integer> pizzaSizeNumber = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, pizzaSizeNumber.get("small").intValue());
        assertEquals(1, pizzaSizeNumber.get("regular").intValue());
    }


    @Test
    public void getOrderTest1() throws Exception {
        ResponseEntity<HashMap<String, Integer>> responseEntity = api.getOrder(6,1);
        HashMap<String, Integer> pizzaSizeNumber = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, pizzaSizeNumber.get("large").intValue());
        assertEquals(1, pizzaSizeNumber.get("small").intValue());
    }


    @Test
    public void getOrderTest2() throws Exception {
        ResponseEntity<HashMap<String, Integer>> responseEntity = api.getOrder(2,0);
        HashMap<String, Integer> pizzaSizeNumber = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, pizzaSizeNumber.get("small").intValue());
    }

}
