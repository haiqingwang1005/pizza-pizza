package io.swagger.repository;

import io.swagger.model.PizzaSize;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaSizesRepository extends MongoRepository<PizzaSize, String> {
  PizzaSize findByName(String name);
  PizzaSize findById(String id);
}
