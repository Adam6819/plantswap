package com.plantswap.plantswap.controllers;

import com.plantswap.plantswap.models.Plant;
import com.plantswap.plantswap.repositories.PlantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plants")
public class PlantController {
    @Autowired
    private PlantRepository plantRepository;

    //HTTP post method
    @PostMapping
    public ResponseEntity<Plant> createPlant(@Valid @RequestBody Plant plant){
        Plant savedPlant = plantRepository.save(plant);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlant);
    }
}
