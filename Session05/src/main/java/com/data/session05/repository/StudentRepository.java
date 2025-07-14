package com.data.session05.repository;

import com.data.session05.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFullNameContaining(String fullName);
    List<Student> findByAddressContaining(String address);
    List<Student> findByClassName(String className);
}
