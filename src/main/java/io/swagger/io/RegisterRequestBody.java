package io.swagger.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

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
}
