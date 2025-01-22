package com.plantswap.plantswap.models;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plants")
public class Plant {
@Id
    private String id;

@NotNull(message = "name can not be null")
@NotEmpty(message = "name can not be empty")
@Size(max = 50, message = "name can not be grater than 50 characters")
    private String name;

String isPresent;

@NotNull(message = "Description can not be null")
@NotEmpty(message = " Description can not be empty")
private String description;

@NotNull(message = "Light requirement can not be null")
@NotEmpty(message = " Light requirement can not be empty")
private String lightRequirement;

@NotNull(message = "Water requirement can not be null")
@NotEmpty(message = "Water requirement can not be empty")
private String waterRequirement;

@NotNull(message = "difficulty can not be null")
@NotEmpty(message = "Difficulty can not be empty")
private String difficulty;

@NotNull(message = "Size can not be null")
@NotEmpty(message = "Sizw can not be empty")
private String size;

@NotNull(message = "plant type can not be null")
@NotEmpty(message = " Plant type can not be empty")
private String plantType;

@NotNull(message = "price can not be null")
@PositiveOrZero(message = "Price can not be negative number")
@Positive(message = "price must be grater than zero")
private String price;

@NotNull(message = "Status can not be null")
@NotEmpty(message = " Status ca not be empty")
private String status;
private String ean;
private String plantUrl;

@DBRef
private User user;


    public Plant() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getPlantUrl() {
        return plantUrl;
    }

    public void setPlantUrl(String plantUrl) {
        this.plantUrl = plantUrl;
    }

    public String getLightRequirement() {
        return lightRequirement;
    }

    public void setLightRequirement(String lightRequirement) {
        this.lightRequirement = lightRequirement;
    }

    public String getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(String waterRequirement) {
        this.waterRequirement = waterRequirement;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(String isPresent) {
        this.isPresent = isPresent;
    }
}
