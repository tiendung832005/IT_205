package com.data.session04.controller;

import com.data.session04.entity.CategoryBook;
import com.data.session04.service.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category-books")
public class CategoryBookController {

    @Autowired
    private CategoryBookService categoryBookService;

    @GetMapping
    public String getCategoryBooks(Model model) {
        List<CategoryBook> categoryBooks = categoryBookService.getCategoryBooks();
        model.addAttribute("categoryBooks", categoryBooks);
        return "category-books";
    }

    @GetMapping("/{id}")
    public String getCategoryBookById(@PathVariable String id, Model model) {
        CategoryBook categoryBook = categoryBookService.getCategoryBookById(id);
        model.addAttribute("categoryBook", categoryBook);
        return "category-book-detail";
    }

    @PostMapping
    public String addCategoryBook(@ModelAttribute CategoryBook categoryBook) {
        categoryBookService.addCategoryBook(categoryBook);
        return "redirect:/category-books";
    }

    @PutMapping("/{id}")
    public String updateCategoryBook(@PathVariable String id, @ModelAttribute CategoryBook categoryBook) {
        categoryBook.setCateBookId(id);
        categoryBookService.updateCategoryBook(categoryBook);
        return "redirect:/category-books";
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryBook(@PathVariable String id) {
        categoryBookService.deleteCategoryBook(id);
        return "redirect:/category-books";
    }
}