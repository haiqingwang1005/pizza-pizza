package io.swagger.model;

import io.swagger.model.PizzaSize;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/piaazsizes")
public class PizzaSizeRes {

    @GetMapping
    public List<PizzaSize> getPizzaSizes() {
        return Arrays.asList(
            new PizzaSize(1,"small", 7),
            new PizzaSize(2,"medium", 9)
        );
    }

    private class PizzaSize {
      private int id;
      private String description;
      private int size;

      public PizzaSize(int id, String description, int size) {
        this.id = id;
        this.description=description;
        this.size= size;
      }

      public int getId() {
        return id;
      }

      public void setId(int id) {
        this.id = id;
      }

      public String getDescription() {
        return description;
      }

      public void setDescription(String description) {
        this.description = description;
      }

      public int getSize() {
        return size;
      }

      public void setSize(int size) {
        this.size = size;
      }
    }
}
