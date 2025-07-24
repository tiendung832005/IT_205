package com.data.session13.controller;


import com.data.session13.model.entity.ProductCart;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @PostMapping("/add")
    public String addProductToCart(@RequestBody ProductCart productCart) {
        return "Product added to cart!";
    }

    @DeleteMapping("/remove/{productId}")
    public String removeProductFromCart(@PathVariable Long productId) {
        return "Product removed from cart!";
    }

    @PutMapping("/update/{productId}")
    public String updateProductQuantity(@PathVariable Long productId, @RequestParam int quantity) {
        return "Product quantity updated!";
    }

    @GetMapping
    public String viewCart() {
        return "Cart items displayed!";
    }
}
