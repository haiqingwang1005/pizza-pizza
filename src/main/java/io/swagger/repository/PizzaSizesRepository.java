package io.swagger.repository;

import io.swagger.model.PizzaSize;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaSizesRepository extends MongoRepository<PizzaSize, String> {
  PizzaSize findByTag(String tag);
}
