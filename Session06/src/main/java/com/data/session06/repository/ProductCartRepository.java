package com.data.session06.repository;

import com.data.session06.entity.ProductCart;
import com.data.session06.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    List<ProductCart> findByUser(User user);
}