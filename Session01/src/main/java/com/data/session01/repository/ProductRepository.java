package com.data.session01.repository;

import com.data.session01.Entity.Product;

import java.util.List;

public interface ProductRepository {
    void save(Product product);
    void update(Product product);
    void delete(Long id);
    Product findById(Long id);
    List<Product> findAll();
}