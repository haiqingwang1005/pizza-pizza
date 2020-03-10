package io.swagger.service;

import io.swagger.repository.StoreLocationRepository;
import io.swagger.model.StoreLocation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreLocationService {
  private final StoreLocationRepository storeLocationRepository;

  @Autowired
  public StoreLocationService(StoreLocationRepository storeLocationRepository) {
    this.storeLocationRepository = storeLocationRepository;
  }

  public List<StoreLocation> getAllStoreLocations() {
    return storeLocationRepository.findAll();
  }
}
