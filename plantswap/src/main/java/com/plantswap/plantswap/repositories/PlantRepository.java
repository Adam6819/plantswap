package com.plantswap.plantswap.repositories;

import com.plantswap.plantswap.models.Plant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlantRepository extends MongoRepository<Plant, String> {

}
