package com.data.session03.repository;

import com.data.session03.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findCategoriesByCateNameContains(String cateName);

}
