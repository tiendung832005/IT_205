package com.data.session05.controller;


import com.data.session05.entity.Product;
import com.data.session05.entity.ProductList;
import com.data.session05.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProductsAsJson() {
        return getSampleProducts();
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ProductList getProductsAsXml() {
        return new ProductList(getSampleProducts());
    }

    private List<Product> getSampleProducts() {
        return Arrays.asList(
                new Product(1L, "Product A", 100.0),
                new Product(2L, "Product B", 200.0),
                new Product(3L, "Product C", 300.0)
        );
    }
}
