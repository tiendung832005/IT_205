package com.data.session14.repository;

import com.data.session14.model.entity.Ticket;
import com.data.session14.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);
}