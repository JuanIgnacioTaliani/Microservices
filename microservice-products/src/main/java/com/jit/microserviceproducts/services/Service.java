package com.jit.microserviceproducts.services;

import java.util.List;

public interface Service<T, ID> {
    void add(T entity);

    void update(T entity);

    void delete(ID id);

    T getById(ID id);

    boolean existsById(ID id);

    List<T> getAll();
}
