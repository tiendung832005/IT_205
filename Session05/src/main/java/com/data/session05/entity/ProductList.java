package com.data.session05.entity;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class ProductList {
    @JacksonXmlElementWrapper(localName = "Products")
    private List<Product> products;

    // Constructor
    public ProductList(List<Product> products) {
        this.products = products;
    }

    // Getters and Setters
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
