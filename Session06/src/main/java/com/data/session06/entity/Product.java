package com.data.session06.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String proId;

    @Column(name = "product_name", length = 100, nullable = false)
    private String proName;

    @Column(name = "producer", length = 100)
    private String producer;

    @Column(name = "year_making")
    private Integer yearMaking;

    @Column(name = "expire_date")
    private Date expireDate;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
}