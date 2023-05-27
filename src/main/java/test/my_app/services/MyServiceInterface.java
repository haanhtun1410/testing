package test.my_app.services;

import java.util.List;

public interface MyServiceInterface<T> {
    List<T> findAll();
    T findById(Long id);
    void save(T entity);
    void delete(T entity);

}