package com.data.session16.repository;

import com.data.session16.model.entity.TicketOrder;
import com.data.session16.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
    List<TicketOrder> findByUser(User user);
}