package com.data.session06.controller;


import com.data.session06.entity.Order;
import com.data.session06.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/date/{date}")
    public List<Order> getOrdersByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return orderService.getOrdersByDate(localDate);
    }
}
