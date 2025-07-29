package com.data.session16.service;

import com.data.session16.model.entity.Combo;
import com.data.session16.model.entity.TicketOrder;
import com.data.session16.model.entity.User;
import com.data.session16.repository.TicketOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketOrderService {
    private final TicketOrderRepository ticketOrderRepository;

    public TicketOrder placeOrder(User user, Integer quantityTicket, List<Combo> combos) {
        BigDecimal totalMoney = calculateTotalMoney(quantityTicket, combos);

        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setUser(user);
        ticketOrder.setQuantityTicket(quantityTicket);
        ticketOrder.setCombos(combos);
        ticketOrder.setTotalMoney(totalMoney);
        ticketOrder.setCreatedAt(LocalDateTime.now());

        return ticketOrderRepository.save(ticketOrder);
    }

    public List<TicketOrder> getUserOrders(User user) {
        return ticketOrderRepository.findByUser(user);
    }

    public List<TicketOrder> getAllOrders() {
        return ticketOrderRepository.findAll();
    }

    private BigDecimal calculateTotalMoney(Integer quantityTicket, List<Combo> combos) {
        BigDecimal ticketPrice = BigDecimal.valueOf(100); // Example ticket price
        BigDecimal total = ticketPrice.multiply(BigDecimal.valueOf(quantityTicket));

        for (Combo combo : combos) {
            total = total.add(combo.getPrice());
        }

        return total;
    }
}