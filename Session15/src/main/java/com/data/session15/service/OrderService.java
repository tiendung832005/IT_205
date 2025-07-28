package com.data.session15.service;

import com.data.session15.model.entity.Order;
import com.data.session15.model.entity.OrderItem;
import com.data.session15.model.entity.Product1;
import com.data.session15.model.entity.User;
import com.data.session15.repository.OrderItemRepository;
import com.data.session15.repository.OrderRepository;
import com.data.session15.repository.ProductRepository1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository1 productRepository1;

    public Order createOrder(Long userId, List<OrderItem> items) {
        BigDecimal totalMoney = items.stream()
                .map(item -> item.getPriceBuy().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setUser(new User(userId, null, null, null, null));
        order.setCreatedDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setTotalMoney(totalMoney);
        order = orderRepository.save(order);

        for (OrderItem item : items) {
            item.setOrder(order);
            orderItemRepository.save(item);
        }

        return order;
    }

    public List<Order> getOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}