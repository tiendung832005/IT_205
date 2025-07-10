package com.data.session03.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @Column(name = "category_id", length = 15)
    private String cateId;
    @Column(name = "category_name", length = 50, nullable = false, unique = true)
    private String cateName;

    @OneToMany
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private java.util.List<Product> products;
}
