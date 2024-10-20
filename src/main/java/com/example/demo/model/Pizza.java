package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Pizza")
public class Pizza implements Identifieble{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @NotBlank(message = "Field is required")
    public String name;

    @NotNull(message = "Field is required")
    public int price;

    @NotNull(message = "Field is required")
    public int size;

    public Pizza() {
    }

    public Pizza(int id, String name, int price, int size) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
