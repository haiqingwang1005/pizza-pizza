package io.swagger.repository;

import io.swagger.model.Apetite;
import io.swagger.model.Calories;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CaloriesRepository extends MongoRepository<Calories, Apetite> {
   Calories getCalorieByApetite(Apetite apetite);
}
