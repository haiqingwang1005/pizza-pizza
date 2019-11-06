package io.swagger.repository;

import io.swagger.model.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditCardsRepository extends MongoRepository<CreditCard, String> {
    CreditCard findByCardNumber(String cardNumber);
}
