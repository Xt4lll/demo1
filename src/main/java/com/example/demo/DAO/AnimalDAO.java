package com.example.demo.DAO;

import com.example.demo.model.Animal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalDAO {

    private static int ANIMAL_COUNT;

    private List<Animal> animals;
    {
        animals = new ArrayList<Animal>();

        animals.add(new Animal(++ANIMAL_COUNT, "бобэр"));
        animals.add(new Animal(++ANIMAL_COUNT, "даун"));
        animals.add(new Animal(++ANIMAL_COUNT, "говно"));
    }

    public List<Animal> index() {
        return animals;
    }

    public Animal show(int id) {
        return animals.stream().filter(animal -> animal.getId() == id).findAny().orElse(null);
    }

}
