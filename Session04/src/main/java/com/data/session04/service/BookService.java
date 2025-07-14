package com.data.session04.service;

import com.data.session04.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    boolean addBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBook(String bookId);
    Book getBookById(String bookId);
    List<Book> getBooksByNameAndAuthor(String bookName, String author);
}
