package io.swagger.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Account {

    @Id
    private String id;

    private String username;

    private String password;

    private String email;

    private String firstname;

    private String lastname;

    private Long createEpoch;

    private AccountRole accountRole;
}
