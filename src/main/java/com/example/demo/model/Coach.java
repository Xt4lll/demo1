package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "coaches")
public class Coach implements Identifieble{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String experience;

    @OneToOne(optional = true, mappedBy = "coach")
    private Team team;

    public Coach() {}

    public Coach(String name, String experience) {
        this.name = name;
        this.experience = experience;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
