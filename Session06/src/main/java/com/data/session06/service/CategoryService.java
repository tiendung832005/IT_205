package com.data.session06.service;

import com.data.session06.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(String categoryId, Category category);
    void deleteCategory(String categoryId);
    Category getCategoryById(String categoryId);
    List<Category> getAllCategories();
}