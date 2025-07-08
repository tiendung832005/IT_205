package com.data.session02.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", length = 10)
    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "screen_room_id", referencedColumnName = "id")
    private ScreenRoom screenRoom;
}