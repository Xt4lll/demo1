package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "sponsonrs")
public class Sponsor implements Identifieble{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String industry;

    @ManyToMany(mappedBy = "sponsors")
    private Set<Team> teams;

    public Sponsor() {}

    public Sponsor(String name, String industry) {
        this.name = name;
        this.industry = industry;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
