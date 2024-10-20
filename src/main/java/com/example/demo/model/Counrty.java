package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Country")
public class Counrty implements Identifieble{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @NotBlank(message = "Field is required")
    public String name;

    @NotBlank(message = "Field is required")
    public String code;

    @NotBlank(message = "Field is required")
    public int population;

    public Counrty() {
    }

    public Counrty(int id, String name, String code, int population) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.population = population;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
