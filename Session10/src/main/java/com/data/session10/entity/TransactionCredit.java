package com.data.session10.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_credits")
public class TransactionCredit {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_receiver_id", nullable = false)
    private Account accountReceiver;

    @ManyToOne
    @JoinColumn(name = "credit_card_sender_id", nullable = false)
    private CreditCard creditCardSender;

    private String note;

    private Double money;

    private String status; // "thành công", "thất bại", "đang chờ xử lý"
}