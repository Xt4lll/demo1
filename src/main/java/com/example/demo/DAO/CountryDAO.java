package com.example.demo.DAO;

import com.example.demo.model.Animal;
import com.example.demo.model.Counrty;
import org.springframework.stereotype.Component;

@Component
public class CountryDAO extends GenericDAO<Counrty> {

    private static int OBJECT_COUNT = 0;

    @Override
    public void save(Counrty obj) {
        if (obj.getId() == 0) {
            obj.setId(++OBJECT_COUNT);
        }
        entities.add(obj);
    }

}
