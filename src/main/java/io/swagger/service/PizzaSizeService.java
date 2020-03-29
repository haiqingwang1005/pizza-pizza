package io.swagger.service;

import io.swagger.repository.PizzaSizesRepository;
import io.swagger.model.PizzaSize;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaSizeService {
    private static final Logger log = LoggerFactory.getLogger(PizzaSizeService.class);
    private final PizzaSizesRepository pizzaSizesRepository;

    @Autowired
    public PizzaSizeService(PizzaSizesRepository pizzaSizesRepository) {
        this.pizzaSizesRepository = pizzaSizesRepository;
    }

    public PizzaSize addPizzaSize(PizzaSize pizzaSize) {
        String newName = pizzaSize.getName();
        String newTitle = pizzaSize.getTitle();
        Long newInch = pizzaSize.getInch();
        Integer newSlices = pizzaSize.getNumberOfSlices();
        Integer newCaloriesPerSlice = pizzaSize.getCaloriesPerSlice();

        if (newName == null) {
            return null;
        }

        PizzaSize existingPizzaSize = pizzaSizesRepository.findByName(newName);
        if (existingPizzaSize != null) {
            log.info(String.format("Pizza size with name %s already exists, modify it.", newName));
            if (newCaloriesPerSlice != null) {
                existingPizzaSize.setCaloriesPerSlice(newCaloriesPerSlice);
            }
            if (newInch != null) {
                existingPizzaSize.setInch(newInch);
            }
            if (newSlices != null) {
                existingPizzaSize.setNumberOfSlices(newSlices);
            }
            if (newTitle != null) {
              existingPizzaSize.setTitle(newTitle);
            }
            return existingPizzaSize;
        }

        if (newInch == null
                || newCaloriesPerSlice == null
                || newSlices == null) {
            return null;
        }

        PizzaSize newPizzaSize = PizzaSize.builder()
                .inch(newInch)
                .name(newName)
                .caloriesPerSlice(newCaloriesPerSlice)
                .numberOfSlices(newSlices)
                .title(newTitle)
                .build();

        pizzaSizesRepository.save(newPizzaSize);

        log.info(
                String.format("New Pizza Size with tag %s has been added!", newName));
        return newPizzaSize;
    }

    public PizzaSize getPizzaSizeByName(String name) {
        PizzaSize existingPizzaSize = pizzaSizesRepository.findByName(name);
        if (existingPizzaSize == null) {
            log.info(String.format("Pizza size with name %s does not exist!", name));
            return null;
        } else {
            return existingPizzaSize;
        }
    }

    public List<PizzaSize> getPizzaSizes() {
        return pizzaSizesRepository.findAll();
    }

    public PizzaSize deletePizzaSizeByName(String name) {
        PizzaSize existingPizzaSize = pizzaSizesRepository.findByName(name);
        if (existingPizzaSize == null) {
            return null;
        }
        pizzaSizesRepository.delete(existingPizzaSize);
        return existingPizzaSize;
    }
}
