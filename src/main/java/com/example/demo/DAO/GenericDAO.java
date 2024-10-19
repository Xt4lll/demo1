package com.example.demo.DAO;

import com.example.demo.model.Identifieble;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;

public class GenericDAO<T extends Identifieble> implements IGenericDAO<T>{
    protected List<T> entities = new ArrayList<T>();

    @Override
    public void save(T entity) {
        entities.add(entity);
    }

    @Override
    public void update(int id, T entity) {
        entities.set(id, entity);
    }

    @Override
    public void delete(int id) {
        entities.remove(id);
    }

    @Override
    public T findById(int id) {
        return entities.stream()
                .filter(entity -> ((T) entity).getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> findAll() {
        return entities;
    }
}
