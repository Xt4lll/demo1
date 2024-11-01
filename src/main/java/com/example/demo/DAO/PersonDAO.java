package com.example.demo.DAO;

import com.example.demo.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO extends GenericDAO<Person> {

    private static int OBJECT_COUNT = 0;

    @Override
    public void save(Person obj) {
        if (obj.getId() == 0) {
            obj.setId(++OBJECT_COUNT);
        }
        entities.add(obj);
    }

}
