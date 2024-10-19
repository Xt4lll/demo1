package com.example.demo.DAO;

import com.example.demo.model.Animal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalDAO extends GenericDAO<Animal> {

    private static int ANIMAL_COUNT = 0;

    @Override
    public void save(Animal animal) {
        if (animal.getId() == 0) {
            animal.setId(++ANIMAL_COUNT);
        }
        entities.add(animal);
    }

}
