package com.example.demo.DAO;

import com.example.demo.model.Game;
import org.springframework.stereotype.Component;

@Component

public class GameDAO extends GenericDAO<Game> {

    private static int OBJECT_COUNT = 0;

    @Override
    public void save(Game obj) {
        if (obj.getId() == 0) {
            obj.setId(++OBJECT_COUNT);
        }
        entities.add(obj);
    }

}
