package com.data.session04.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;;
    @Column(name = "book_name", length = 100, nullable = false, unique = true)
    private String bookName;
    @Column(name = "author", length = 100, nullable = false)
    private String author;
    @Column(name = "publisher", length = 100)
    private String publisher;
    @Column(name = "price")
    private Double price;
    @Column(name = "year_publish")
    private Integer yearPublish;

    @ManyToOne
    @JoinColumn(name = "cateId",referencedColumnName = "cate_book_id")
    private CategoryBook categoryBook;

}
