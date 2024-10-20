package com.example.demo.repositories;

import com.example.demo.model.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends BaseRepository<Game, Integer>{
    List<Game> findByNameContaining(String name);
}
