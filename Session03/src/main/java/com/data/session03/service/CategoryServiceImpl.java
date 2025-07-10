package com.data.session03.service;

import com.data.session03.entity.Category;
import com.data.session03.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean insertCategory(Category cate) {
        if (cate == null || cate.getCateId() == null || cate.getCateName() == null) {
            return false;
        }
        categoryRepository.save(cate);
        return true;
    }

    @Override
    public boolean updateCategory(Category cate, String cateId) {
        if (cate == null || cateId == null || !categoryRepository.existsById(cateId)) {
            return false;
        }
        cate.setCateId(cateId);
        categoryRepository.save(cate);
        return true;
    }

    @Override
    public boolean deleteCategory(String cateId) {
        if (cateId == null || !categoryRepository.existsById(cateId)) {
            return false;
        }
        categoryRepository.deleteById(cateId);
        return true;
    }

    @Override
    public List<Category> searchCategories(String cateName) {
        if (cateName == null || cateName.isEmpty()) {
            return categoryRepository.findAll();
        }
        return categoryRepository.findCategoriesByCateNameContains(cateName);
    }

    @Override
    public Category getCategoryById(String cateId) {
        if (cateId == null || !categoryRepository.existsById(cateId)) {
            return null;
        }
        return categoryRepository.findById(cateId).orElse(null);
    }
}
