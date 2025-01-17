package com.plantswap.plantswap.controllers;

import com.plantswap.plantswap.models.Plant;
import com.plantswap.plantswap.repositories.PlantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants(){
        List<Plant> plants = plantRepository.findAll();
        return ResponseEntity.ok(plants);
    }
    //Hämta en planta genom id
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById (@PathVariable String id){
        Plant plant = plantRepository.findById(id)
                // Om id inte finns så får vi "plant not found" - felhantering
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found"));
        return ResponseEntity.ok(plant);
    }

    @PutMapping("{id})")
    public ResponseEntity<Plant> updatePlant(@PathVariable String id, @RequestBody Plant plantDetails){
        Plant existingPlant = plantRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND," Plant not found"));

        existingPlant.setName(plantDetails.getDescription());
        existingPlant.setDescription(plantDetails.getDescription());
        existingPlant.setDifficulty(plantDetails.getDifficulty());
        existingPlant.setPlantType(plantDetails.getPlantType());
        existingPlant.setLightRequirement(plantDetails.getLightRequirement());
        existingPlant.setWaterRequirement(plantDetails.getWaterRequirement());
        existingPlant.setPrice(plantDetails.getPrice());
        existingPlant.setStatus(plantDetails.getStatus());
        existingPlant.setSize(plantDetails.getSize());

        return ResponseEntity.ok(plantRepository.save(existingPlant));
    }
}
