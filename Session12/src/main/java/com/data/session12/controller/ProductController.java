package com.data.session12.controller;

import com.data.session12.entity.Product;
import com.data.session12.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/findAll")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "Product added successfully!";
    }

    @PutMapping("/{id}")
    public String editProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.editProduct(id, product);
        return "Product updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully!";
    }
}
