package io.github.vicen621.blogmensajes.persistance.dao;

import java.util.List;

public interface BaseDAO<T> {
    List<T> findAll();
    T findById(long id);
    void create(T entity);
    void update(T entity);
    void delete(T entity);
}
