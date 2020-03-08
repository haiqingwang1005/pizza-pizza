package io.swagger.repository;

import io.swagger.model.Appetite;
import io.swagger.model.Calories;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CaloriesRepository extends MongoRepository<Calories, Appetite> {
   Calories getCalorieByAppetite(Appetite appetite);
}
