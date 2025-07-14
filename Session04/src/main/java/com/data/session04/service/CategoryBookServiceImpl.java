package com.data.session04.service;

import com.data.session04.entity.CategoryBook;
import com.data.session04.repository.CategoryBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBookServiceImpl implements CategoryBookService {

    @Autowired
    private CategoryBookRepository categoryBookRepository;

    @Override
    public List<CategoryBook> getCategoryBooks() {
        return categoryBookRepository.findAll();
    }

    @Override
    public boolean addCategoryBook(CategoryBook categoryBook) {
        if (categoryBook != null) {
            categoryBookRepository.save(categoryBook);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCategoryBook(CategoryBook categoryBook) {
        if (categoryBook != null && categoryBookRepository.existsById(categoryBook.getCateBookId())) {
            categoryBookRepository.save(categoryBook);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategoryBook(String cateBookId) {
        if (cateBookId != null && categoryBookRepository.existsById(cateBookId)) {
            categoryBookRepository.deleteById(cateBookId);
            return true;
        }
        return false;
    }

    @Override
    public CategoryBook getCategoryBookById(String cateBookId) {
        return categoryBookRepository.findById(cateBookId).orElse(null);
    }

    @Override
    public List<CategoryBook> getCategoryBooksByName(String cateBookName) {
        return categoryBookRepository.findCategoryBookByCateBookName(cateBookName);
    }
}