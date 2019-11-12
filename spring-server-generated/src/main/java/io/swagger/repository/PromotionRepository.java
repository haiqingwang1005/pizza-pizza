package io.swagger.repository;

import io.swagger.model.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PromotionRepository extends MongoRepository<Promotion, String> {
    Promotion findByCode(String code);
}
