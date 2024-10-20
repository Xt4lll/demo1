package com.example.demo.DAO;

import com.example.demo.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryDAO extends GenericDAO<Country> {

    private static int OBJECT_COUNT = 0;

    @Override
    public void save(Country obj) {
        if (obj.getId() == 0) {
            obj.setId(++OBJECT_COUNT);
        }
        entities.add(obj);
    }

}
