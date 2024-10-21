package com.example.demo.repositories;

import com.example.demo.model.Player;
import com.example.demo.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Override
    List<Player> findAll();

    List<Player> findByNameContaining(String name);
}
