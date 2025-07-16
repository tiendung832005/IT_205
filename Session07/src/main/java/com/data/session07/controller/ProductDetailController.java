package com.data.session07.controller;

import com.data.session07.entity.ProductDetail;
import com.data.session07.service.ProductDetailService;
import com.data.session07.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-details")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping
    public List<ProductDetail> getAllProductDetails() {
        return productDetailService.getAllProductDetails();
    }

    @PostMapping
    public ProductDetail createProductDetail(@RequestBody ProductDetail productDetail){
        return productDetailService.createProductDetail(productDetail);
    }

    @PutMapping("/{id}")
    public ProductDetail updateProductDetail(@PathVariable Long id, @RequestBody ProductDetail productDetail){
        return productDetailService.updateProductDetail(id, productDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteProductDetail(@PathVariable Long id) {
        productDetailService.deleteProductDetail(id);
    }
}
