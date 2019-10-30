package io.swagger.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.swagger.model.Toppings;

public interface ToppingsRepository extends MongoRepository<Toppings, UUID> {
  public Toppings findByName(String name);

  public List<Toppings> findByIsGlutenFreeIsTrue();
}