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
private String fullName;

@NotNull(message = "age can not be null")
@NotEmpty(message = "Age can not be empty")
@PositiveOrZero(message = "Age can not be negative")
@Positive(message = "Age can not be zero")
private int age;


    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
