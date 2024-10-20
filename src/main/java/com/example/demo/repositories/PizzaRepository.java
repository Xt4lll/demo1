package com.example.demo.repositories;

import com.example.demo.model.Pizza;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends BaseRepository<Pizza, Integer> {
    List<Pizza> findByNameContaining(String name);
}