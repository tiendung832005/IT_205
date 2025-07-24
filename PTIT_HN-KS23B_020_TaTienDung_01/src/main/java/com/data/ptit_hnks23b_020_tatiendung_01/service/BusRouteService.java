package com.data.ptit_hnks23b_020_tatiendung_01.service;

import com.data.ptit_hnks23b_020_tatiendung_01.entity.BusRoute;
import com.data.ptit_hnks23b_020_tatiendung_01.repository.BusRouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteService {
    private final BusRouteRepository busRouteRepository;

    public BusRouteService(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

    public List<BusRoute> getAllBusRoutes(){
        return busRouteRepository.findAllByOrderByStartPointAsc();
    }

    // Thêm
    public BusRoute addBusRoute(BusRoute busRoute){
        busRoute.setStatus(true);
        return busRouteRepository.save(busRoute);
    }

    // Sửa
    public BusRoute updateBusRoute(Integer id, BusRoute busRoute) {
        BusRoute existingBusRoute = busRouteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chuyến xe buýt không tồn tại"));
        existingBusRoute.setStartPoint(busRoute.getStartPoint());
        existingBusRoute.setEndPoint(busRoute.getEndPoint());
        existingBusRoute.setTripInformation(busRoute.getTripInformation());
        existingBusRoute.setDriverName(busRoute.getDriverName());
        existingBusRoute.setStatus(busRoute.getStatus());
        return busRouteRepository.save(existingBusRoute);
    }

    // Xóa
    public void deleteBusRoute(Integer id) {
        BusRoute busRoute = busRouteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chuyến xe buýt không tồn tại"));
        busRoute.setStatus(false);
        busRouteRepository.save(busRoute);
    }

    // Tìm kiếm
    public List<BusRoute> searchBusRoutes(String startPoint, String endPoint, Integer id) {
        if (id != null) {
            return busRouteRepository.findById(id).map(List::of).orElseThrow(() -> new IllegalArgumentException("Chuyến xe buýt không tồn tại"));
        }
        return busRouteRepository.findByStartPointContainingAndEndPointContaining(startPoint, endPoint);
    }
}
