package com.plantswap.plantswap.controllers;

import com.plantswap.plantswap.models.Plant;
import com.plantswap.plantswap.models.User;
import com.plantswap.plantswap.repositories.PlantRepository;
import com.plantswap.plantswap.repositories.UserRepository;
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

    //så vi kan komma åt userRepository i våra metoder
    @Autowired
    private UserRepository userRepository;

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

    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable String id, @Valid @RequestBody Plant plant){
        Plant existingPlant = plantRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND," Plant not found"));
        // När vi uppdaterar vi det som behöver ändras
        existingPlant.setName(plant.getName());
        existingPlant.setDescription(plant.getDescription());
        existingPlant.setPlantType(plant.getPlantType());
        existingPlant.setEan(plant.getEan());
        existingPlant.setPlantUrl(plant.getPlantUrl());
        existingPlant.setPrice(plant.getPrice());
        existingPlant.setWaterRequirement(plant.getWaterRequirement());
        existingPlant.setLightRequirement(plant.getLightRequirement());
        existingPlant.setDifficulty(plant.getDifficulty());
        existingPlant.setSize(plant.getSize());
        existingPlant.setStatus(plant.getStatus());
//Innan vi uppdaterar så måste vi kolla om ID finns i databasen - Om inte inte finns så kastar vi ett fel, felhantering.
        if (!plantRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"plant not found");

      //kolla om rätt plant finns i databasen
        if (plant.getUser() !=null){
            // kolla om rätt User finns i Repository genom ID - går först i plant sen hämtar user och baserad på den hämtar vi ID på just rätt user
            User user = userRepository.findById(plant.getUser().getId())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "plant not found"));
            plant.setUser(user);
        }

        //Här lägger jag senare en transaction relaterad kod, (förmodlingen)

        Plant updatePlant = plantRepository.save(plant);
        return ResponseEntity.ok(updatePlant);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plant> deletePlant(@PathVariable String id){
        if (!plantRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found");
        }

        plantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
