package com.plantswap.plantswap.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plants")
public class Plant {
@Id
    private String id;
    private String name;
    private String description;
    private String size;
    private String plantType;

}
