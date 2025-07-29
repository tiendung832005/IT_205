package com.data.session16.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Role {
        ROLE_STAFF, ROLE_ADMIN
    }

    public enum Status {
        ACTIVE, INACTIVE
    }
}