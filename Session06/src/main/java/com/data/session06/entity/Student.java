package com.data.session06.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @Column(name = "stu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stuId;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "gender", nullable = false)
    private Boolean gender;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "address", length = 255)
    private String address;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    private Class classEntity;
}