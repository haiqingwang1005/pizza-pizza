package io.swagger.repository;

import io.swagger.model.PizzaSize;
import io.swagger.model.Toppings;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaSizesRepository extends MongoRepository<PizzaSize, Long> {
  PizzaSize findById(Long id);
}
