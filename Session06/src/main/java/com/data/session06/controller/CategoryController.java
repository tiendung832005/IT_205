package com.data.session06.controller;

import com.data.session06.entity.Category;
import com.data.session06.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") String categoryId, @RequestBody Category category) {
        return categoryService.updateCategory(categoryId, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") String categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}