package org.example.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(long id);

    List<T> findAll(int from, int pageSize);

    Long save(T t);

    void update(T t);

    void delete(T t);
}