package com.plantswap.plantswap.models;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plants")
public class Plant {
@Id
    private String id;

@NotNull(message = "name can not be null")
@NotEmpty(message = "name can not be empty")
@Size(max = 50, message = "name can not be grater than 50 characters")
    private String name;

// Descriptions ska innehålla: ljusbehov, vattenbehov och svårighetsgrad
@NotNull(message = "Description can not be null")
@NotEmpty(message = " Description can not be empty")
private String description;
private String size;

@NotNull(message = "plant type can not be null")
private String plantType;

@NotNull(message = "price can not be null")
@PositiveOrZero(message = "Price can not be negative number")
@Positive(message = "price must be grater than zero")
private String price;

@NotNull(message = "Status can not be null")
@NotEmpty(message = " Status ca not be empty")
private String status;
private String isbn;
private String plantUrl;


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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPlantUrl() {
        return plantUrl;
    }

    public void setPlantUrl(String plantUrl) {
        this.plantUrl = plantUrl;
    }
}
