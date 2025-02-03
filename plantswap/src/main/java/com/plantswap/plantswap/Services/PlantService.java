package com.plantswap.plantswap.Services;

import com.plantswap.plantswap.models.Plant;
import com.plantswap.plantswap.repositories.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PlantService {
    @Autowired
    private PlantRepository plantRepository;

    public Plant createPlant(Plant plant) {
        //kolla name, price and status
        if (plant.getName() == null || plant.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Plant name can not be empty");
        }

        if (plant.getPrice() < 0) {
            throw new IllegalArgumentException("Plant price can not be empty");

        }



        return plantRepository.save(plant);
    }
}
