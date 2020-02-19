package io.swagger.repository;

import static org.junit.Assert.assertEquals;

import io.swagger.model.Apetite;
import io.swagger.model.Calories;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:/application-test.properties")
public class CaloriesRepositoryTest {

  @Autowired
  private CaloriesRepository caloriesRepository;

  @Before
  public void setUp() {
    caloriesRepository.deleteAll();
    Calories calories1 = new Calories().apetitite(Apetite.ADULT).numberOfCaloriesRequired(2000.0);
    Calories calories2 = new Calories().apetitite(Apetite.CHILD).numberOfCaloriesRequired(1200.0);
    caloriesRepository.insert(calories1);
    caloriesRepository.insert(calories2);
  }

  @Test
  public void testSize() {
    assertEquals(2, caloriesRepository.findAll().size());
  }

  @Test
  public void getCaloriesForAudult() {
    assertEquals(2000,
        caloriesRepository.getCalorieByApetite(Apetite.ADULT).getNumberOfCaloriesRequired(), 0.1);
  }

  @Test
  public void getCaloriesForChild() {

    assertEquals(1200,
        caloriesRepository.getCalorieByApetite(Apetite.CHILD).getNumberOfCaloriesRequired(), 0.1);
  }
}
