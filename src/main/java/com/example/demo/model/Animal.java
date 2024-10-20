package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Table(name="Animal")
public class Animal implements Identifieble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @NotBlank(message = "Field is required")
    public String name;

    @NotBlank(message = "Field is required")
    public String type;

    @NotBlank(message = "Field is required")
    public int amount;

    public Animal() {}

    public Animal(int id, String name, String type, int amount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
