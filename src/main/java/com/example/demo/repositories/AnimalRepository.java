package com.example.demo.repositories;

import com.example.demo.model.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends BaseRepository<Animal, Integer> {
    List<Animal> findByNameContaining(String name);
}
