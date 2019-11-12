package io.swagger.repository;

import io.swagger.model.StoreLocation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreLocationRepository extends MongoRepository<StoreLocation, String> {
  StoreLocation findByName(String name);
}
