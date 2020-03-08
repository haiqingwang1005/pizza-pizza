package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

  @JsonProperty("numberOfAdult")
  private Integer numberOfAdult;
  @JsonProperty("numberOfChild")
  private Integer numberOfChild;
}
