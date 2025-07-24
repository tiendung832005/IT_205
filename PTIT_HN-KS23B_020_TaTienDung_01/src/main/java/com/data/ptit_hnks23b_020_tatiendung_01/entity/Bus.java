package com.data.ptit_hnks23b_020_tatiendung_01.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "bus")
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Integer busId;

    @Column(name = "bus_name", nullable = false, unique = true, length = 100)
    private String busName;

    @Column(name = "registration_number", nullable = false, unique = true, length = 30)
    private String registrationNumber;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "image_bus", length = 255)
    private String imageBus;

    @Column(name = "status", nullable = false, columnDefinition = "BIT DEFAULT 1")
    private Boolean status;
}
