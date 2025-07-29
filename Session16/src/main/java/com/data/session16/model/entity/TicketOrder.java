package com.data.session16.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer quantityTicket;

    @ManyToMany
    @JoinTable(
            name = "ticket_order_combos",
            joinColumns = @JoinColumn(name = "ticket_order_id"),
            inverseJoinColumns = @JoinColumn(name = "combo_id")
    )
    private List<Combo> combos;

    @Column(nullable = false)
    private BigDecimal totalMoney;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}