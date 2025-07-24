package com.data.ptit_hnks23b_020_tatiendung_01.service;

import com.data.ptit_hnks23b_020_tatiendung_01.entity.Bus;
import com.data.ptit_hnks23b_020_tatiendung_01.repository.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    private final BusRepository busRepository;

    public BusService(BusRepository busRepository){
        this.busRepository = busRepository;
    }

    // Lấy ds
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    // Thêm
    public Bus addBus(Bus bus){
        if (busRepository.existsByBusName(bus.getBusName())) {
            throw new IllegalArgumentException("Tên xe buýt phải là duy nhất");
        }
        return busRepository.save(bus);
    }

    //Sửa
    public Bus updateBus(Integer id, Bus bus) {
        Bus existingBus = busRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bus not found"));
        if (!existingBus.getBusName().equals(bus.getBusName()) && busRepository.existsByBusName(bus.getBusName())) {
            throw new IllegalArgumentException("Tên xe buýt phải là duy nhất");
        }
        existingBus.setBusName(bus.getBusName());
        existingBus.setRegistrationNumber(bus.getRegistrationNumber());
        existingBus.setTotalSeats(bus.getTotalSeats());
        existingBus.setStatus(bus.getStatus());
        return busRepository.save(existingBus);
    }

    // Xóa
    public void deleteBus(Integer id) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bus not found"));
        if (Boolean.FALSE.equals(bus.getStatus())) {
            throw new IllegalArgumentException("Xe buýt không thể xóa vì nó đang hoạt động");
        }
        bus.setStatus(false);
        busRepository.save(bus);
    }
}

