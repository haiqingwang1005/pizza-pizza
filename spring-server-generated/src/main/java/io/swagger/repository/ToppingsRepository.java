package io.swagger.repository;

import io.swagger.model.Toppings;
import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToppingsRepository extends MongoRepository<Toppings, UUID> {
  Toppings findByName(String name);

  List<Toppings> findByIsGlutenFree(Boolean isGlutenFree);

  List<Toppings> findByIsPremium(Boolean isPremium);
}
