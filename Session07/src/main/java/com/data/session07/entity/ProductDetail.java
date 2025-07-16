package com.data.session07.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_detail")

public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productDetailId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer yearMaking;
    private String color;
    private String size;
    private Double price;

    // Getters and Setters
}