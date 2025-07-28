package com.data.session15.repository;

import com.data.session15.model.entity.Product1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository1 extends JpaRepository<Product1, Long> {
}