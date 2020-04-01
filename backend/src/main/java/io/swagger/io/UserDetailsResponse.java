package io.swagger.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.model.Account;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = UserDetailsResponse.UserDetailsResponseBuilder.class)
@Builder(builderClassName = "UserDetailsResponseBuilder", toBuilder = true)
public class UserDetailsResponse {
    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("role")
    private String role;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserDetailsResponseBuilder {
    }

    public static UserDetailsResponse fromAccount(Account account) {
        return UserDetailsResponse.builder()
                .email(account.getEmail())
                .username(account.getUsername())
                .firstname(account.getFirstname())
                .lastname(account.getLastname())
                .role(account.getAccountRole().getRole())
                .build();
    }
}
