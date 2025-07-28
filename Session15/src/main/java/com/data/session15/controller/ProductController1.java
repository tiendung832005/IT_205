package com.data.session15.controller;

import com.data.session15.model.entity.Product1;
import com.data.session15.service.ProductService1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products1")
@RequiredArgsConstructor
public class ProductController1 {

    private final ProductService1 productService1;

    @GetMapping
    public List<Product1> getAllProducts() {
        return productService1.getAllProducts();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public Product1 createProduct(@RequestBody Product1 product) {
        return productService1.createProduct(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public Product1 updateProduct(@PathVariable Long id, @RequestBody Product1 product) {
        return productService1.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public void deleteProduct(@PathVariable Long id) {
        productService1.deleteProduct(id);
    }
}