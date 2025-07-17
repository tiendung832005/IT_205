package com.data.session08.service;

import com.data.session08.entity.Order;
import com.data.session08.entity.OrderDetail;
import com.data.session08.repository.OrderDetailRepository;
import com.data.session08.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Transactional
    public Order addOrder(Order order, List<OrderDetail> orderDetails) {
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        for (OrderDetail detail : orderDetails) {
            detail.setPriceBuy(detail.getDish().getPrice());
            orderDetailRepository.save(detail);
        }

        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}