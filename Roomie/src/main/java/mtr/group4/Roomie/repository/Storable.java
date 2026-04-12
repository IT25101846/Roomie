package mtr.group4.Roomie.repository;

import java.util.ArrayList;
import java.util.Optional;

public interface Storable<T> {
    ArrayList<T> findAll();
    Optional<T>  findById(String id);
    void         save(T entity);
    void         update(T entity);
    void         delete(String id);
    String       generateId();
}