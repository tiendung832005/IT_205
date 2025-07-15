package com.data.session06.service;

import com.data.session06.entity.ProductCart;
import com.data.session06.entity.User;
import com.data.session06.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartService {

    @Autowired
    private ProductCartRepository productCartRepository;

    public List<ProductCart> getCartItemsByUser(User user) {
        return productCartRepository.findByUser(user);
    }

    public ProductCart addToCart(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    public ProductCart updateQuantity(Long id, Integer quantity) {
        ProductCart existingCartItem = productCartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        existingCartItem.setQuantity(quantity);
        return productCartRepository.save(existingCartItem);
    }

    public void removeFromCart(Long id) {
        productCartRepository.deleteById(id);
    }
}