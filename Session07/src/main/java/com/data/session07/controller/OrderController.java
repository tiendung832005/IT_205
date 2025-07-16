package com.data.session07.controller;

import com.data.session07.entity.Order;
import com.data.session07.entity.OrderDetail;
import com.data.session07.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/place-order")
    public Order placeOrder(@RequestBody Order order, @RequestBody List<OrderDetail> orderDetails) {
        return orderService.placeOrder(order, orderDetails);
    }

    @GetMapping("/user-orders/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/top-buyers")
    public List<Object[]> getTopBuyers() {
        return orderService.getTopBuyers();
    }
}