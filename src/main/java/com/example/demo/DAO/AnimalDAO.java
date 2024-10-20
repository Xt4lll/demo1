package com.example.demo.DAO;

import com.example.demo.model.Animal;
import org.springframework.stereotype.Component;

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
