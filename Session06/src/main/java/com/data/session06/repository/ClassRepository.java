package com.data.session06.repository;

import com.data.session06.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, String> {
}