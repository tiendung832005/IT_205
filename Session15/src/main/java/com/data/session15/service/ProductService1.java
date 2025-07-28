package com.data.session15.service;

import com.data.session15.model.entity.Product1;
import com.data.session15.repository.ProductRepository1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService1 {

    private final ProductRepository1 productRepository1;

    public List<Product1> getAllProducts() {
        return productRepository1.findAll();
    }

    public Product1 createProduct(Product1 product) {
        return productRepository1.save(product);
    }

    public Product1 updateProduct(Long id, Product1 product) {
        Product1 existingProduct = productRepository1.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setSize(product.getSize());
        existingProduct.setToppings(product.getToppings());
        return productRepository1.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository1.deleteById(id);
    }
}