package com.example.demo.repositories;

import com.example.demo.model.Sponsor;
import com.example.demo.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor, Integer> {

    @Override
    List<Sponsor> findAll();

    List<Sponsor> findByNameContaining(String name);
}
