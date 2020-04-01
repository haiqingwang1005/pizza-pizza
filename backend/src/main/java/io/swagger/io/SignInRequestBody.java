package io.swagger.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = SignInRequestBody.SignInBodyBuilder.class)
@Builder(builderClassName = "SignInBodyBuilder", toBuilder = true)
public class SignInRequestBody {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonPOJOBuilder(withPrefix = "")
    public static class SignInBodyBuilder {
    }
}
