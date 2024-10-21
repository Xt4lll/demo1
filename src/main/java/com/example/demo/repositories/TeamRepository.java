package com.example.demo.repositories;

import com.example.demo.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

    @Override
    List<Team> findAll();

    List<Team> findByNameContaining(String name);
}
