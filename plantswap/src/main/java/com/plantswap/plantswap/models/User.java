package com.plantswap.plantswap.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {

@Id
private String id;

@NotNull(message = "Full name can not be null")
@NotEmpty(message = "Full name can not be empty")
private String name;

@NotNull(message = "age can not be null")
@NotEmpty(message = "Age can not be empty")
@PositiveOrZero(message = "Age can not be negative")
@Positive(message = "Age can not be zero")
private String age;


    public User() {
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

    public void setName(String fullName) {
        this.name = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
