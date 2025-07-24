package com.data.session13.model.entity;


import com.data.session13.model.entity.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receiver;
    private String address;
    private String phoneNumber;
    private double totalMoney;
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}