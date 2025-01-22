package com.plantswap.plantswap.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "transactions")
public class Transactions {
    @Id
    private String id;

    @NotNull(message = "amount can not be null")
    @NotEmpty(message = "amount can not be empty")
    boolean amount;
    String payment;

    public Transactions() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getAmount() {
        return amount;
    }

    public void setAmount(boolean amount) {
        this.amount = amount;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
