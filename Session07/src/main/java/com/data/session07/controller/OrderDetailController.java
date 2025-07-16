package com.data.session07.controller;

import com.data.session07.entity.OrderDetail;
import com.data.session07.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    @PostMapping
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.createOrderDetail(orderDetail);
    }

    @PutMapping("/{id}")
    public OrderDetail updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        return orderDetailService.updateOrderDetail(id, orderDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);
    }
}