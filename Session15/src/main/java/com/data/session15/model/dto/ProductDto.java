package com.data.session15.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private String producer;
    private int yearMaking;
    private LocalDate expireDate;
    private int quantity;
    private double price;
}