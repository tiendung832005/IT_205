package com.data.session06.controller;

import com.data.session06.entity.ProductCart;
import com.data.session06.entity.User;
import com.data.session06.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ProductCartController {

    @Autowired
    private ProductCartService productCartService;

    @GetMapping
    public List<ProductCart> getCartItemsByUser(@RequestParam User user) {
        return productCartService.getCartItemsByUser(user);
    }

    @PostMapping
    public ProductCart addToCart(@RequestBody ProductCart productCart) {
        return productCartService.addToCart(productCart);
    }

    @PutMapping("/{id}")
    public ProductCart updateQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        return productCartService.updateQuantity(id, quantity);
    }

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable Long id) {
        productCartService.removeFromCart(id);
    }
}