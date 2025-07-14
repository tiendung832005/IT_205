package com.data.session04.service;

import com.data.session04.entity.Book;
import com.data.session04.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public boolean addBook(Book book) {
        if (book != null) {
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        if (book != null && bookRepository.existsById(String.valueOf(book.getBookId()))) {
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBook(String bookId) {
        if (bookId != null && bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }

    @Override
    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }


    @Override
    public List<Book> getBooksByNameAndAuthor(String bookName, String author) {
        return bookRepository.findBooksByBookNameAndAuthor(bookName, author);
    }

}