package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Game")
public class Game implements Identifieble{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @NotBlank(message = "Field is required")
    public String name;

    @NotBlank(message = "Field is required")
    public String description;

    @NotNull(message = "Field is required")
    public int online;

    public Game() {
    }

    public Game(int id, String name, String description, int online) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.online = online;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }
}
