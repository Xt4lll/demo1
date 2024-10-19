package com.example.demo.model;

public class Game implements Identifieble{

    public int id;
    public String name;
    public String description;
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
