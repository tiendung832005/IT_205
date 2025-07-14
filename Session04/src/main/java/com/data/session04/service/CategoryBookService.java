package com.data.session04.service;

import com.data.session04.entity.CategoryBook;

import java.util.List;

public interface CategoryBookService {
    List<CategoryBook> getCategoryBooks();
    boolean addCategoryBook(CategoryBook categoryBook);
    boolean updateCategoryBook(CategoryBook categoryBook);
    boolean deleteCategoryBook(String cateBookId);
    CategoryBook getCategoryBookById(String cateBookId);
    List<CategoryBook> getCategoryBooksByName(String cateBookName);
}