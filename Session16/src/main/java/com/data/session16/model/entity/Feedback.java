package com.data.session16.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "play_area_id")
    private PlayArea playArea;

    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo combo;

    @Column(nullable = false)
    private Integer rating;

    private String comment;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private String reply;

    private LocalDateTime repliedAt;
}