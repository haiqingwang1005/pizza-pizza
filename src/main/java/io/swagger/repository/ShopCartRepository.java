package io.swagger.repository;

import io.swagger.model.ShopCart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopCartRepository extends MongoRepository<ShopCart, String> {
    ShopCart findByUsername(String username);
}
