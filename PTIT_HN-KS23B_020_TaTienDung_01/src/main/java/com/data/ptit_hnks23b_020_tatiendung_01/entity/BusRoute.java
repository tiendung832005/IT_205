package com.data.ptit_hnks23b_020_tatiendung_01.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bus_route")
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_route_id")
    private Integer busRouteId;

    @Column(name = "start_point", nullable = false, length = 255)
    private String startPoint;

    @Column(name = "end_point", nullable = false, length = 255)
    private String endPoint;

    @Column(name = "trip_information", nullable = false, length = 255)
    private String tripInformation;

    @Column(name = "driver_name", nullable = false, length = 70)
    private String driverName;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;
}
