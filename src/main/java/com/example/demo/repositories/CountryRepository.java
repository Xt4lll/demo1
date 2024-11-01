package com.example.demo.repositories;

import com.example.demo.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends BaseRepository<Country, Integer>{
    List<Country> findByNameContaining(String name);
}
