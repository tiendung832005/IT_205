package com.data.session05.entity;


import java.time.LocalDate;

public class FruitDTO {
    private String name;
    private Double price;
    private Integer stock;
    private Boolean status;
    private LocalDate createdAt;

    // Constructor, Getters, and Setters
    public FruitDTO(String name, Double price, Integer stock, Boolean status, LocalDate createdAt) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Boolean getStatus() {
        return status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}