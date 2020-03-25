package io.swagger.api;

import io.swagger.model.Crust;
import io.swagger.service.CrustService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CrustsApiController implements CrustsApi {
    private static final Logger log = LoggerFactory.getLogger(CrustsApiController.class);

    private final CrustService crustService;

    @Autowired
    public CrustsApiController(CrustService crustService) {
        this.crustService = crustService;
    }

    @Override
    public ResponseEntity<Crust> addCrust(Crust body) {
        Crust crust = crustService.addCrust(body);
        if (crust == null) {
            return new ResponseEntity<Crust>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<Crust>(crust, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Crust> deleteCrust(String searchName) {
        Crust exitingCrust = crustService.deleteCrust(searchName);
        if (exitingCrust == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exitingCrust, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Crust>> searchCrust(String searchName, Boolean isGlutenFree) {
        List<Crust> crusts = crustService.findCrusts(searchName, isGlutenFree);
        if (crusts.isEmpty()) {
            return new ResponseEntity<>(crusts, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(crusts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> getCrustImage(String name) {
        ClassPathResource imgFile = new ClassPathResource(String.format("image/crusts/%s.jpg", name));
        try {
            byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
