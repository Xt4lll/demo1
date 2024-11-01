package com.example.demo.repositories;

import com.example.demo.model.Coach;
import com.example.demo.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Integer> {

    @Override
    List<Coach> findAll();

    List<Coach> findByNameContaining(String name);
}
