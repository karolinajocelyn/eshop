package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface CrudRepository<T> {
    T create(T t);
    T findById(String id);
    Iterator<T> findAll();
    void update(String id, T t);
    void delete(String id);
}
