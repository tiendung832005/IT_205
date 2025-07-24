package com.data.session13.model.entity;


import jakarta.persistence.*;

@Entity
public class ProductCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    private int quantity;

    // Getters and Setters
}
