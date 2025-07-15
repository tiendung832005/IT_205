package com.data.session06.service;

import com.data.session06.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(String productId, Product product);
    void deleteProduct(String productId);
    Product getProductById(String productId);
    List<Product> getAllProducts();
}