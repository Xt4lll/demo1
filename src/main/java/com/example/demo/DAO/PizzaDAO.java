package com.example.demo.DAO;

import com.example.demo.model.Pizza;
import org.springframework.stereotype.Component;

@Component
public class PizzaDAO extends GenericDAO<Pizza> {

    private static int OBJECT_COUNT = 0;

    @Override
    public void save(Pizza obj) {
        if (obj.getId() == 0) {
            obj.setId(++OBJECT_COUNT);
        }
        entities.add(obj);
    }

}
