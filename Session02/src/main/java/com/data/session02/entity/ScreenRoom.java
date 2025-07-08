package com.data.session02.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "screen_rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreenRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "theater_id", referencedColumnName = "id")
    private Theater theater;

    @OneToMany(mappedBy = "screenRoom")
    private List<Seat> seats;

    @OneToMany(mappedBy = "screenRoom")
    private List<Showtime> showtimes;
}