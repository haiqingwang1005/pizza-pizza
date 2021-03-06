package io.swagger.repository;

import io.swagger.model.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends MongoRepository<Pizza, String> {
    Pizza findByDisplayName(String displayName);

    Pizza findByCrustNameAndSizeNameAndToppingName(String crust, String size, String topping);

    Pizza findById(String id);
}
