package com.data.session15.controller;

import com.data.session15.model.entity.Order;
import com.data.session15.model.entity.OrderItem;
import com.data.session15.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public Order createOrder(@RequestParam Long userId, @RequestBody List<OrderItem> items) {
        return orderService.createOrder(userId, items);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<Order> getOrderHistory(@RequestParam Long userId) {
        return orderService.getOrderHistory(userId);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('STAFF')")
    public Order updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        return orderService.updateOrderStatus(id, status);
    }
}