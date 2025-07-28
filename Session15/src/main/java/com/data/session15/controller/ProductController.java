package com.data.session15.controller;

import com.data.session15.model.dto.ProductDto;
import com.data.session15.model.entity.Product;
import com.data.session15.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProducts(keyword));
    }

    @GetMapping("/available")
    public ResponseEntity<List<ProductDto>> getAvailableProducts() {
        return ResponseEntity.ok(productService.getAvailableProducts());
    }

    @GetMapping("/new")
    public ResponseEntity<List<ProductDto>> getNewProducts() {
        return ResponseEntity.ok(productService.getNewProducts());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}