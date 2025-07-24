package com.data.session13.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private com.data.session13.entity.Order order;

    @ManyToOne
    private Product product;

    private int quantity;
    private double priceBuy;

    // Getters and Setters
}
