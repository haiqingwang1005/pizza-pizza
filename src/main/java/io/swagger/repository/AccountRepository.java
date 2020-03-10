package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findById(String id);
    Account findByUsername(String username);
}
