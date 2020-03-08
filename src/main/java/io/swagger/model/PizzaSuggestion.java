package io.swagger.model;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PizzaSuggestion {
  private Map<String, Integer> suggestion;
}
