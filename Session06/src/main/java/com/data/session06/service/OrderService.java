package com.data.session06.service;

import com.data.session06.entity.Order;
import com.data.session06.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByDate(LocalDate date) {
        return orderRepository.findAll().stream()
                .filter(order -> order.getCreatedAt().toLocalDate().equals(date))
                .toList();
    }
}