package io.swagger.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.repository.AccountRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@Data
@Builder
@JsonDeserialize(builder = Account.AccountBuilder.class)
public class Account {
    public static void initialize(AccountRepository accountRepository,
                                  PasswordEncoder passwordEncoder) {
        if (accountRepository.findByUsername("testadmin") == null) {
            Account adminAccount = Account.builder()
                    .accountRole(AccountRole.ADMIN)
                    .username("testadmin")
                    .password(passwordEncoder.encode("testpassword"))
                    .createEpoch(Instant.now().getEpochSecond())
                    .email("admin@test.com")
                    .firstname("test")
                    .lastname("admin")
                    .build();
            accountRepository.save(adminAccount);
        }
    }
    @Id
    private String id;

    private String username;

    private String password;

    private String email;

    private String firstname;

    private String lastname;

    private Long createEpoch;

    private AccountRole accountRole;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountBuilder {
    }
}
