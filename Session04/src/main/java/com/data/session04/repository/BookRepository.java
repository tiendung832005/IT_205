package com.data.session04.repository;

import com.data.session04.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findBooksByBookNameAndAuthor(String bookName, String author);

}
