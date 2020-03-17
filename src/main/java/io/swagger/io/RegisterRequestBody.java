package io.swagger.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.model.Account;
import io.swagger.model.AccountRole;
import io.swagger.service.AccountService;
import io.swagger.utils.Sanitizer;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@JsonDeserialize(builder = RegisterRequestBody.RegisterRequestBodyBuilder.class)
@Builder(builderClassName = "RegisterRequestBodyBuilder", toBuilder = true)
public class RegisterRequestBody {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("accountRole")
    private String accountRole;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RegisterRequestBodyBuilder {
    }

    public static Account toAccount(RegisterRequestBody body,
                                    Sanitizer sanitizer) throws AccountService.AuthenticationException {

        if (StringUtils.isEmpty(body.getUsername()) || StringUtils.isEmpty(body.getPassword())) {
            throw new AccountService.AuthenticationException(AccountService.AuthenticationError.InvalidInput);
        }

        String username = sanitizer.sanitize(body.getUsername());
        String password = sanitizer.sanitize(body.getPassword());
        String firstname = sanitizer.sanitize(body.getFirstname());
        String lastname = sanitizer.sanitize(body.getLastname());
        String email = sanitizer.sanitize(body.getEmail());
        String role = sanitizer.sanitize(body.getAccountRole());


        if (!StringUtils.equals(username, body.getUsername())
                || !StringUtils.equals(password, body.getPassword())
                || !StringUtils.equals(firstname, body.getFirstname())
                || !StringUtils.equals(lastname, body.getLastname())
                || !StringUtils.equals(email, body.getEmail())
                || !StringUtils.equals(role, body.getAccountRole())) {
            throw new AccountService.AuthenticationException(AccountService.AuthenticationError.InvalidInput);
        }
        return Account.builder()
                .username(body.getUsername().toLowerCase())
                .password(body.getPassword())
                .firstname(body.getFirstname())
                .lastname(body.getLastname())
                .email(body.getEmail())
                .accountRole(AccountRole.fromValue(role))
                .build();
    }

}
