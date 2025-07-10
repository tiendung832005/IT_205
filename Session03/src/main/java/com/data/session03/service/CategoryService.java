package com.data.session03.service;

import com.data.session03.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    boolean insertCategory(Category cate);
    boolean updateCategory(Category cate, String cateId);
    boolean deleteCategory(String cateId);
    List<Category> searchCategories(String cateName);
    // search by id
    Category getCategoryById(String cateId);
}
