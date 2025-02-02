package com.plantswap.plantswap.repositories;

import com.plantswap.plantswap.models.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlantRepository extends MongoRepository<Plant, String> {
 //filtrera efter namn
    List<Plant> findByName (String name);

    List<Plant> findByPriceBetween(double minPrice, double maxPrice);

    List<Plant> findBySize (String size);

}
