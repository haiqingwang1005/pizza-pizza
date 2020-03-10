package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = Customer.CustomerBuilder.class)
@Builder(builderClassName = "CustomerBuilder", toBuilder = true)
public class Customer {

  @JsonProperty("numberOfAdult")
  private Integer numberOfAdult;
  @JsonProperty("numberOfChild")
  private Integer numberOfChild;

  @JsonPOJOBuilder(withPrefix = "")
  public static class CustomerBuilder {
  }
}
