package com.example.demo.DAO;

import java.util.List;

public interface IGenericDAO<T> {
    void save(T entity);
    void update(int id, T entity);
    void delete(int id);
    T findById(int id);
    List<T> findAll();
}
