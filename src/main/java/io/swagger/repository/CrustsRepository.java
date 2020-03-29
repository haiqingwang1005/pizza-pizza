package io.swagger.repository;

import io.swagger.model.Crust;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CrustsRepository  extends MongoRepository<Crust, String> {
    List<Crust> findByIsGlutenFree(Boolean isGlutenFree);
    Crust findByName(String name);
    Crust findById(String id);
}
