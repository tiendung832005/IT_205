package com.data.session02.service;

import java.util.Optional;

public interface IService<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    T update(T entity);
    void delete(ID id);
}