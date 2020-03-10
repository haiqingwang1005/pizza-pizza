package io.swagger.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = PizzaSuggestion.PizzaSuggestionBuilder.class)
@Builder(builderClassName = "PizzaSuggestionBuilder", toBuilder = true)public class PizzaSuggestion {
  @JsonProperty("suggestion")
  private Map<String, Integer> suggestion;

  @JsonPOJOBuilder(withPrefix = "")
  public static class PizzaSuggestionBuilder {
  }
}
