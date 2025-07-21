package com.data.session10.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Account receiver;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Account sender;

    private Double money;

    private String note;

    private String status; // "thành công", "thất bại", "đang chờ xử lý"

    private LocalDateTime createdAt;
}