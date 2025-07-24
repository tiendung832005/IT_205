package com.data.ptit_hnks23b_020_tatiendung_01.repository;

import com.data.ptit_hnks23b_020_tatiendung_01.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRouteRepository extends JpaRepository<BusRoute,Integer> {
    List<BusRoute> findAllByOrderByStartPointAsc();
    List<BusRoute> findByStartPointContainingAndEndPointContaining(String startPoint, String endPoint);
}
