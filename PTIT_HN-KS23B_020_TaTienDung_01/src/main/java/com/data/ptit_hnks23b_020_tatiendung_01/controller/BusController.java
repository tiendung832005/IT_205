package com.data.ptit_hnks23b_020_tatiendung_01.controller;


import com.data.ptit_hnks23b_020_tatiendung_01.dto.ApiResponse;
import com.data.ptit_hnks23b_020_tatiendung_01.entity.Bus;
import com.data.ptit_hnks23b_020_tatiendung_01.service.BusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Bus>>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thành công", buses, HttpStatus.OK.value()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Bus>> addBus(@RequestBody Bus bus) {
        Bus newBus = busService.addBus(bus);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Thêm thành công", newBus, HttpStatus.CREATED.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Bus>> updateBus(@PathVariable Integer id, @RequestBody Bus bus) {
        Bus updatedBus = busService.updateBus(id, bus);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật thành công", updatedBus, HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBus(@PathVariable Integer id) {
        busService.deleteBus(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Xóa thành công", null, HttpStatus.OK.value()));
    }
}
