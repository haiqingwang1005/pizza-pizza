package io.swagger.repository;

import io.swagger.model.Toppings;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToppingsRepository extends MongoRepository<Toppings, String> {
  Toppings findByName(String name);

  List<Toppings> findByIsPremium(Boolean isPremium);
}
