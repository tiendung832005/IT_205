package com.data.ptit_hnks23b_020_tatiendung_01.repository;

import com.data.ptit_hnks23b_020_tatiendung_01.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Integer> {
    boolean existsByBusName(String busName);
}
