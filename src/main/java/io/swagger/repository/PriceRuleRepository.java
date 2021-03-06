package io.swagger.repository;

import io.swagger.model.PriceRule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRuleRepository extends MongoRepository<PriceRule, String> {
}
