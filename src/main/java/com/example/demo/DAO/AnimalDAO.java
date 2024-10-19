package com.example.demo.DAO;

import com.example.demo.model.Animal;
import com.example.demo.model.Counrty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalDAO extends GenericDAO<Animal> {

    private static int OBJECT_COUNT = 0;

    @Override
    public void save(Animal obj) {
        if (obj.getId() == 0) {
            obj.setId(++OBJECT_COUNT);
        }
        entities.add(obj);
    }

}
