package com.data.session15.repository;

import com.data.session15.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNameContainingIgnoreCase(String keyword);
    List<Product> findByQuantityGreaterThan(int quantity);
    List<Product> findByYearMaking(int year);
    List<Product> findByCategoryId(Long categoryId);
}
