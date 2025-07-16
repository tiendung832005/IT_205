package com.data.session07.service;

import com.data.session07.entity.Order;
import com.data.session07.entity.OrderDetail;
import com.data.session07.repository.OrderDetailRepository;
import com.data.session07.repository.OrderRepository;
import com.data.session07.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;

    public Order placeOrder(Order order, List<OrderDetail> orderDetails) {
        Order savedOrder = orderRepository.save(order);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrder(savedOrder);
            orderDetailRepository.save(orderDetail);
        }
        return savedOrder;
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserUserId(userId);
    }

    public List<Object[]> getTopBuyers() {
        return null;
    }
}