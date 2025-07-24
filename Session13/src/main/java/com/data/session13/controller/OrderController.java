package com.data.session13.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @PostMapping("/checkout")
    public String checkoutOrder(@RequestBody com.data.session13.entity.Order order) {
        return "Order placed successfully!";
    }
}
