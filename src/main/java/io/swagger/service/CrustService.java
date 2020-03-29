package io.swagger.service;

import io.swagger.model.Crust;
import io.swagger.repository.CrustsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CrustService {
    private static final Logger log = LoggerFactory.getLogger(CrustService.class);

    private final CrustsRepository crustsRepository;

    @Autowired
    public CrustService(CrustsRepository crustsRepository) {
        this.crustsRepository = crustsRepository;
    }

    public Crust addCrust(Crust crust) {
        String name = crust.getName();
        String title = crust.getTitle();
        Boolean isGlutenFree = crust.getIsGlutenFree();
        String desc = crust.getDescription();

        if (name == null || name.trim().equals("")) {
            return null;
        }

        Crust existingCrust = crustsRepository.findByName(name);
        if (existingCrust != null) {
            log.info(String.format("PizzaPizza crust name %s already exists! override it!", name));
            existingCrust.setIsGlutenFree(isGlutenFree);
            existingCrust.setDescription(desc);
            existingCrust.setTitle(title);
            crustsRepository.save(existingCrust);
            return existingCrust;
        }

        Crust newCrust = Crust.builder()
                .name(name)
                .isGlutenFree(isGlutenFree)
                .title(title)
                .description(desc)
                .build();
        crustsRepository.insert(newCrust);

        log.info(
                String.format("Crust name: %s, title %s, gluten free: %b, description: %s",
                        name, title, isGlutenFree, desc));
        return newCrust;
    }

    public List<Crust> findCrusts(String crustName, Boolean isGlutenFree) {
        List<Crust> crusts = new ArrayList<>();
        if (crustName != null) {
            Crust result = crustsRepository.findByName(crustName);
            if (result != null) {
                crusts.add(result);
            }
        } else {
            Set<Crust> set = new HashSet<>(crustsRepository.findAll());
            if (isGlutenFree != null) {
                set.retainAll(crustsRepository.findByIsGlutenFree(isGlutenFree));
            }
            crusts.addAll(set);
        }
        return crusts;
    }

    public Crust deleteCrust(String searchName) {
        if (searchName == null) {
            return null;
        }
        Crust exitingCrust = crustsRepository.findByName(searchName);
        crustsRepository.delete(exitingCrust);
        return exitingCrust;
    }
}
