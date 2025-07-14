package com.data.session04.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories_book")
public class CategoryBook {

    @Id
    @Column(name = "cate_book_id", length = 20)
    private String cateBookId;

    @Column(name = "cate_book_name", length = 100, nullable = false, unique = true)
    private String cateBookName;

    @OneToMany(mappedBy = "categoryBook", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;
}